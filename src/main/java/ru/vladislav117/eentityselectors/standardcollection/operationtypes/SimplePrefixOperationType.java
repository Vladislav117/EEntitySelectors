package ru.vladislav117.eentityselectors.standardcollection.operationtypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.operation.Operation;

import java.util.Collection;

public class SimplePrefixOperationType extends PrefixOperationType {
    protected Operation operation;

    public SimplePrefixOperationType(String id, Collection<String> prefixes, Operation operation) {
        super(id, prefixes);
        this.operation = operation;
    }

    public SimplePrefixOperationType(String id, String prefix, Operation operation) {
        super(id, prefix);
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public @Nullable Operation readOperation(String query) {
        return operation;
    }
}
