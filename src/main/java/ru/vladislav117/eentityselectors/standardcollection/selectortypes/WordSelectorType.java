package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.selector.SelectorType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class WordSelectorType extends SelectorType {
    protected List<String> words;

    public WordSelectorType(String id, Collection<String> words) {
        super(id);
        this.words = new ArrayList<>(words);
    }

    public WordSelectorType(String id, String word) {
        super(id);
        words = new ArrayList<>();
        words.add(word);
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public @Nullable Selector read(String query) {
        for (String word : words) {
            if (!query.equals(word)) continue;
            Selector selector = readSelector(query);
            if (selector == null) continue;
            return selector;
        }
        return null;
    }

    public abstract Selector readSelector(String query);
}
