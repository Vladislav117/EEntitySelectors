package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.selector.SelectorType;
import ru.vladislav117.eentityselectors.standardcollection.selectors.StringPropertySelector;
import ru.vladislav117.eentityselectors.utils.StringUtils;

import java.util.*;

public class StringPropertySelectorType extends SelectorType {
    static String equalitySymbol = "=";
    static String suitableValuesSplitter = "\\|";

    public static String getEqualitySymbol() {
        return equalitySymbol;
    }

    public static void setEqualitySymbol(String equalitySymbol) {
        StringPropertySelectorType.equalitySymbol = equalitySymbol;
    }

    public static String getSuitableValuesSplitter() {
        return suitableValuesSplitter;
    }

    public static void setSuitableValuesSplitter(String suitableValuesSplitter) {
        StringPropertySelectorType.suitableValuesSplitter = suitableValuesSplitter;
    }

    protected List<String> propertyNames;
    protected StringPropertySelector.StringPropertyGetter propertyGetter;
    protected boolean caseSensitive = true;

    public StringPropertySelectorType(String id, Collection<String> propertyNames, StringPropertySelector.StringPropertyGetter propertyGetter) {
        super(id);
        this.propertyNames = new ArrayList<>(propertyNames);
        this.propertyGetter = propertyGetter;
    }

    public StringPropertySelectorType(String id, String propertyName, StringPropertySelector.StringPropertyGetter propertyGetter) {
        super(id);
        propertyNames = new ArrayList<>();
        propertyNames.add(propertyName);
        this.propertyGetter = propertyGetter;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public StringPropertySelector.StringPropertyGetter getPropertyGetter() {
        return propertyGetter;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public StringPropertySelectorType setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    @Override
    public @Nullable Selector read(String query) {
        for (String propertyName : propertyNames) {
            if (!query.startsWith(propertyName + equalitySymbol)) continue;
            List<String> suitableValues = new ArrayList<>(List.of(StringUtils.removePrefix(query, propertyName + equalitySymbol).split(suitableValuesSplitter)));
            if (!caseSensitive) suitableValues.replaceAll(String::toLowerCase);
            return new StringPropertySelector(suitableValues, propertyGetter).setCaseSensitive(caseSensitive);
        }
        return null;
    }
}
