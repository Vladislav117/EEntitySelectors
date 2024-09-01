package ru.vladislav117.eentityselectors.source;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.selection.Selection;

public abstract class Source {
    public abstract @Nullable EntitySet getEntities(Selection selection);
}
