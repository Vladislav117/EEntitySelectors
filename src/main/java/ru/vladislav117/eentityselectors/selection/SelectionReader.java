package ru.vladislav117.eentityselectors.selection;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.environment.EEntitySelectorsEnvironment;
import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.operation.OperationType;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.selector.SelectorType;
import ru.vladislav117.eentityselectors.source.Source;
import ru.vladislav117.eentityselectors.source.SourceType;
import ru.vladislav117.eentityselectors.utils.DoubleObject;

import java.util.ArrayList;
import java.util.List;

public class SelectionReader {
    static String stepSplitter = ",";

    public static String getStepSplitter() {
        return stepSplitter;
    }

    public static void setStepSplitter(String stepSplitter) {
        SelectionReader.stepSplitter = stepSplitter;
    }

    protected EEntitySelectorsEnvironment environment;
    protected String query;
    protected int currentStepIndex;
    protected List<String> stepsQueries;
    protected List<SelectionStep> steps;

    public SelectionReader(EEntitySelectorsEnvironment environment, String query) {
        this.environment = environment;
        this.query = query;
        currentStepIndex = -1;
        stepsQueries = List.of(query.split(stepSplitter));
        steps = new ArrayList<>();
    }

    public String getQuery() {
        return query;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public List<String> getStepsQueries() {
        return stepsQueries;
    }

    public @Nullable String getCurrentStepQuery() {
        if (currentStepIndex < 0 || currentStepIndex >= stepsQueries.size()) return null;
        return stepsQueries.get(currentStepIndex);
    }

    public List<SelectionStep> getSteps() {
        return steps;
    }

    public @Nullable SelectionStep getCurrentStep() {
        if (currentStepIndex < 0 || currentStepIndex >= stepsQueries.size()) return null;
        return steps.get(currentStepIndex);
    }

    public SelectionStatus readStep() {
        if (currentStepIndex == stepsQueries.size() - 1) return SelectionStatus.OTHER_ERROR;
        currentStepIndex++;
        Operation operation = null;
        String noOperationQuery = stepsQueries.get(currentStepIndex);
        for (OperationType operationType : environment.getOperationTypes()) {
            DoubleObject<Operation, String> readResult = operationType.read(stepsQueries.get(currentStepIndex));
            if (readResult == null) continue;
            operation = readResult.getFirst();
            noOperationQuery = readResult.getSecond();
            break;
        }
        if (operation == null) {
            if (environment.getDefaultOperation() == null) return SelectionStatus.OPERATION_ERROR;
            operation = environment.getDefaultOperation();
        }

        Source source = null;
        String noOperationNoSourceQuery = noOperationQuery;
        for (SourceType sourceType : environment.getSourceTypes()) {
            DoubleObject<Source, String> readResult = sourceType.read(noOperationQuery);
            if (readResult == null) continue;
            source = readResult.getFirst();
            noOperationNoSourceQuery = readResult.getSecond();
            break;
        }
        if (source == null) {
            if (environment.getDefaultSource() == null) return SelectionStatus.SOURCE_ERROR;
            source = environment.getDefaultSource();
        }

        Selector selector = null;
        for (SelectorType selectorType : environment.getSelectorTypes()) {
            Selector readResult = selectorType.read(noOperationNoSourceQuery);
            if (readResult == null) continue;
            selector = readResult;
            break;
        }
        if (selector == null) {
            if (environment.getDefaultSelectorType() == null) return SelectionStatus.SELECTOR_ERROR;
            selector = environment.getDefaultSelectorType().read(noOperationNoSourceQuery);
            if (selector == null) return SelectionStatus.SELECTOR_ERROR;
        }

        steps.add(new SelectionStep(operation, source, selector));
        return SelectionStatus.OK;
    }

    public SelectionStatus read() {
        while (currentStepIndex < stepsQueries.size() - 1) {
            SelectionStatus status = readStep();
            if (status.isError()) return status;
        }
        return SelectionStatus.OK;
    }

    public Selection newSelection(@Nullable Entity initiator) {
        return new Selection(environment, steps, initiator);
    }

    public Selection newSelection() {
        return new Selection(environment, steps, null);
    }
}
