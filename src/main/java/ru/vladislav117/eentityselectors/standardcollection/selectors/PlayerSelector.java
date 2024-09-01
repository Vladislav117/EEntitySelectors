package ru.vladislav117.eentityselectors.standardcollection.selectors;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.selector.Selector;

public class PlayerSelector extends Selector {
    @Override
    public @Nullable Boolean isSuitable(Entity entity, Selection selection) {
        return entity.getType().equals(EntityType.PLAYER);
    }
}
