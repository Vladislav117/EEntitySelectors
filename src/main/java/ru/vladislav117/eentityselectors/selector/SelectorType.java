package ru.vladislav117.eentityselectors.selector;

import org.jetbrains.annotations.Nullable;

public abstract class SelectorType {
    protected final String id;

    public SelectorType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract @Nullable Selector read(String query);
}
