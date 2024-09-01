package ru.vladislav117.eentityselectors.standardcollection.sourcetypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.source.Source;
import ru.vladislav117.eentityselectors.source.SourceType;
import ru.vladislav117.eentityselectors.utils.DoubleObject;
import ru.vladislav117.eentityselectors.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class PrefixSourceType extends SourceType {
    protected List<String> prefixes;

    public PrefixSourceType(String id, Collection<String> prefixes) {
        super(id);
        this.prefixes = new ArrayList<>(prefixes);
    }

    public PrefixSourceType(String id, String prefix) {
        super(id);
        prefixes = new ArrayList<>();
        prefixes.add(prefix);
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    @Override
    public @Nullable DoubleObject<Source, String> read(String query) {
        for (String prefix : prefixes) {
            if (!query.startsWith(prefix)) continue;
            Source source = readSource(query);
            if (source == null) continue;
            return new DoubleObject<>(source, StringUtils.removePrefix(query, prefix));
        }
        return null;
    }

    public abstract @Nullable Source readSource(String query);
}
