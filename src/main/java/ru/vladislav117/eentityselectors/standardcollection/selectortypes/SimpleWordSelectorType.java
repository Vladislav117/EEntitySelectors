package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import ru.vladislav117.eentityselectors.selector.Selector;

import java.util.Collection;

public class SimpleWordSelectorType extends WordSelectorType {
    protected Selector selector;

    public SimpleWordSelectorType(String id, Collection<String> words, Selector selector) {
        super(id, words);
        this.selector = selector;
    }

    public SimpleWordSelectorType(String id, String word, Selector selector) {
        super(id, word);
        this.selector = selector;
    }

    public Selector getSelector() {
        return selector;
    }

    @Override
    public Selector readSelector(String query) {
        return selector;
    }
}
