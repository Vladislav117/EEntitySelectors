package ru.vladislav117.eentityselectors.selection;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.environment.EEntitySelectorsEnvironment;
import ru.vladislav117.eentityselectors.entity.EntitySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Selection {
    protected EEntitySelectorsEnvironment environment;
    protected int currentStepIndex;
    protected List<SelectionStep> steps;
    protected @Nullable Entity initiator;
    protected EntitySet entities;

    public Selection(EEntitySelectorsEnvironment environment, Collection<SelectionStep> steps, @Nullable Entity initiator) {
        this.environment = environment;
        currentStepIndex = -1;
        this.steps = new ArrayList<>(steps);
        this.initiator = initiator;
        entities = new EntitySet();
    }

    public EEntitySelectorsEnvironment getEnvironment() {
        return environment;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public List<SelectionStep> getSteps() {
        return steps;
    }

    public @Nullable SelectionStep getCurrentStep() {
        if (currentStepIndex < 0 || currentStepIndex >= steps.size()) return null;
        return steps.get(currentStepIndex);
    }

    public @Nullable Entity getInitiator() {
        return initiator;
    }

    public EntitySet getEntities() {
        return entities;
    }

    public SelectionStatus executeStep() {
        if (currentStepIndex == steps.size() - 1) return SelectionStatus.OTHER_ERROR;
        currentStepIndex++;
        SelectionStep step = steps.get(currentStepIndex);
        EntitySet sourceEntities = step.getSource().getEntities(this);
        if (sourceEntities == null) return SelectionStatus.SOURCE_ERROR;

        EntitySet selectedEntities = new EntitySet();
        for (Entity entity : sourceEntities.getEntities()) {
            Boolean selectorChoice = step.getSelector().isSuitable(entity, this);
            if (selectorChoice == null) return SelectionStatus.SELECTOR_ERROR;
            if (selectorChoice) selectedEntities.add(entity);
        }

        EntitySet finalEntities = step.getOperation().apply(entities.clone(), selectedEntities, this);
        if (finalEntities == null) return SelectionStatus.OPERATION_ERROR;
        entities = finalEntities;
        return SelectionStatus.OK;
    }

    public SelectionStatus execute() {
        while (currentStepIndex < steps.size() - 1) {
            SelectionStatus status = executeStep();
            if (status.isError()) return status;
        }
        return SelectionStatus.OK;
    }
}
