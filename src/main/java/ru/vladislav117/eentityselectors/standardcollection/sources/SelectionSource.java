package ru.vladislav117.eentityselectors.standardcollection.sources;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.source.Source;

public class SelectionSource extends Source {
    @Override
    public @Nullable EntitySet getEntities(Selection selection) {
        return selection.getEntities().clone();
    }
}
