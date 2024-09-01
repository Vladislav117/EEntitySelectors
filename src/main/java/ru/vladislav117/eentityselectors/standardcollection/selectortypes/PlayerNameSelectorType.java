package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.selector.SelectorType;
import ru.vladislav117.eentityselectors.standardcollection.selectors.PlayerNameSelector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerNameSelectorType extends SelectorType {
    static String nameSplitter = "\\|";

    public static String getNameSplitter() {
        return nameSplitter;
    }

    public static void setNameSplitter(String nameSplitter) {
        PlayerNameSelectorType.nameSplitter = nameSplitter;
    }

    static Set<Character> allowedSymbols = new HashSet<>() {{
        for (char character : "abcdefghijklmnopqrstuvwxyz0123456789_".toCharArray()) add(character);
    }};

    public static Set<Character> getAllowedSymbols() {
        return allowedSymbols;
    }

    public static void setAllowedSymbols(Set<Character> allowedSymbols) {
        PlayerNameSelectorType.allowedSymbols = allowedSymbols;
    }

    public PlayerNameSelectorType(String id) {
        super(id);
    }

    @Override
    public @Nullable Selector read(String query) {
        query = query.toLowerCase();
        String[] names = query.split(nameSplitter);
        List<String> suitableNames = new ArrayList<>();
        for (String name : names) {
            for (char character : name.toCharArray()) {
                if (!allowedSymbols.contains(character)) return null;
            }
            suitableNames.add(name);
        }
        return new PlayerNameSelector(suitableNames);
    }
}
