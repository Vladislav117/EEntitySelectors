package ru.vladislav117.eentityselectors.standardcollection.sources;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.source.Source;

public class AllWorldsSource extends Source {
    @Override
    public @Nullable EntitySet getEntities(Selection selection) {
        EntitySet entities = new EntitySet();
        for (World world : Bukkit.getWorlds()) {
            entities.add(world.getEntities());
        }
        return entities;
    }
}
