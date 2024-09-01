package ru.vladislav117.eentityselectors.standardcollection.selectors;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.selector.Selector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StringPropertySelector extends Selector {
    protected Set<String> suitableValues;
    protected StringPropertyGetter propertyGetter;
    protected boolean caseSensitive = true;

    public StringPropertySelector(Collection<String> suitableValues, StringPropertyGetter propertyGetter) {
        this.suitableValues = new HashSet<>(suitableValues);
        this.propertyGetter = propertyGetter;
    }

    public StringPropertySelector(String suitableValue, StringPropertyGetter propertyGetter) {
        suitableValues = new HashSet<>();
        suitableValues.add(suitableValue);
        this.propertyGetter = propertyGetter;
    }

    public Set<String> getSuitableValues() {
        return suitableValues;
    }

    public StringPropertyGetter getPropertyGetter() {
        return propertyGetter;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public StringPropertySelector setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    @Override
    public @Nullable Boolean isSuitable(Entity entity, Selection selection) {
        String value = propertyGetter.getProperty(entity);
        if (value == null) return false;
        if (!caseSensitive) value = value.toLowerCase();
        return suitableValues.contains(value);
    }

    @FunctionalInterface
    public interface StringPropertyGetter {
        @Nullable String getProperty(Entity entity);
    }
}
