package ru.vladislav117.eentityselectors.source;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.utils.DoubleObject;

public abstract class SourceType {
    protected final String id;

    public SourceType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract @Nullable DoubleObject<Source, String> read(String query);
}
