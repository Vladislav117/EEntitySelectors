package ru.vladislav117.eentityselectors.standardcollection.sourcetypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.source.Source;

import java.util.Collection;

public class SimplePrefixSourceType extends PrefixSourceType {
    protected Source source;

    public SimplePrefixSourceType(String id, Collection<String> prefixes, Source source) {
        super(id, prefixes);
        this.source = source;
    }

    public SimplePrefixSourceType(String id, String prefix, Source source) {
        super(id, prefix);
        this.source = source;
    }

    @Override
    public @Nullable Source readSource(String query) {
        return source;
    }
}
