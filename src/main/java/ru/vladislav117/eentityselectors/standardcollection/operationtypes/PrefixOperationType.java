package ru.vladislav117.eentityselectors.standardcollection.operationtypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.operation.OperationType;
import ru.vladislav117.eentityselectors.utils.DoubleObject;
import ru.vladislav117.eentityselectors.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PrefixOperationType extends OperationType {
    protected List<String> prefixes;

    public PrefixOperationType(String id, Collection<String> prefixes) {
        super(id);
        this.prefixes = new ArrayList<>(prefixes);
    }

    public PrefixOperationType(String id, String prefix) {
        super(id);
        prefixes = new ArrayList<>();
        prefixes.add(prefix);
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    @Override
    public @Nullable DoubleObject<Operation, String> read(String query) {
        for (String prefix : prefixes) {
            if (!query.startsWith(prefix)) continue;
            Operation operation = readOperation(query);
            if (operation == null) continue;
            return new DoubleObject<>(operation, StringUtils.removePrefix(query, prefix));
        }
        return null;
    }

    public abstract @Nullable Operation readOperation(String query);
}
