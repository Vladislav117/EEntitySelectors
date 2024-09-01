package ru.vladislav117.eentityselectors.selector;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selection.Selection;

public abstract class Selector {
    public abstract @Nullable Boolean isSuitable(Entity entity, Selection selection);
}
