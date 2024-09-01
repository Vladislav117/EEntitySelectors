package ru.vladislav117.eentityselectors.environment;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.operation.OperationType;
import ru.vladislav117.eentityselectors.selection.SelectionReader;
import ru.vladislav117.eentityselectors.selector.SelectorType;
import ru.vladislav117.eentityselectors.source.Source;
import ru.vladislav117.eentityselectors.source.SourceType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EEntitySelectorsEnvironment {
    protected Map<String, OperationType> operationTypes;
    protected Map<String, SourceType> sourceTypes;
    protected Map<String, SelectorType> selectorTypes;
    protected @Nullable Operation defaultOperation;
    protected @Nullable Source defaultSource;
    protected @Nullable SelectorType defaultSelectorType;

    public EEntitySelectorsEnvironment() {
        operationTypes = new HashMap<>();
        sourceTypes = new HashMap<>();
        selectorTypes = new HashMap<>();
    }

    public Collection<OperationType> getOperationTypes() {
        return operationTypes.values();
    }

    public Collection<SourceType> getSourceTypes() {
        return sourceTypes.values();
    }

    public Collection<SelectorType> getSelectorTypes() {
        return selectorTypes.values();
    }

    public @Nullable Operation getDefaultOperation() {
        return defaultOperation;
    }

    public EEntitySelectorsEnvironment setDefaultOperation(@Nullable Operation defaultOperation) {
        this.defaultOperation = defaultOperation;
        return this;
    }

    public @Nullable Source getDefaultSource() {
        return defaultSource;
    }

    public EEntitySelectorsEnvironment setDefaultSource(@Nullable Source defaultSource) {
        this.defaultSource = defaultSource;
        return this;
    }

    public @Nullable SelectorType getDefaultSelectorType() {
        return defaultSelectorType;
    }

    public EEntitySelectorsEnvironment setDefaultSelectorType(@Nullable SelectorType defaultSelectorType) {
        this.defaultSelectorType = defaultSelectorType;
        return this;
    }

    public @Nullable OperationType getOperationType(String id) {
        return operationTypes.get(id);
    }

    public @Nullable SourceType getSourceType(String id) {
        return sourceTypes.get(id);
    }

    public @Nullable SelectorType getSelectorType(String id) {
        return selectorTypes.get(id);
    }

    public boolean add(OperationType operationType) {
        if (operationTypes.containsKey(operationType.getId())) return false;
        operationTypes.put(operationType.getId(), operationType);
        return true;
    }

    public boolean add(SourceType sourceType) {
        if (sourceTypes.containsKey(sourceType.getId())) return false;
        sourceTypes.put(sourceType.getId(), sourceType);
        return true;
    }

    public boolean add(SelectorType selectorType) {
        if (selectorTypes.containsKey(selectorType.getId())) return false;
        selectorTypes.put(selectorType.getId(), selectorType);
        return true;
    }

    public SelectionReader newReader(String query) {
        return new SelectionReader(this, query);
    }
}
