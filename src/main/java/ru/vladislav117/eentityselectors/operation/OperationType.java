package ru.vladislav117.eentityselectors.operation;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.utils.DoubleObject;

public abstract class OperationType {
    protected final String id;

    public OperationType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract @Nullable DoubleObject<Operation, String> read(String query);
}
