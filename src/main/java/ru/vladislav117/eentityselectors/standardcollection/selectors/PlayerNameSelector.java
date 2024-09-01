package ru.vladislav117.eentityselectors.standardcollection.selectors;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.selector.Selector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlayerNameSelector extends Selector {
    protected Set<String> suitableNames;

    public PlayerNameSelector(Collection<String> suitableNames) {
        this.suitableNames = new HashSet<>();
        for (String suitableName : suitableNames) {
            this.suitableNames.add(suitableName.toLowerCase());
        }
    }
    public PlayerNameSelector(String suitableName) {
        suitableNames = new HashSet<>();
        suitableNames.add(suitableName.toLowerCase());
    }

    public Set<String> getSuitableNames() {
        return suitableNames;
    }

    @Override
    public @Nullable Boolean isSuitable(Entity entity, Selection selection) {
        if (!(entity instanceof Player player)) return false;
        return suitableNames.contains(player.getName().toLowerCase());
    }
}
