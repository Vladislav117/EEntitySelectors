package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.selector.SelectorType;
import ru.vladislav117.eentityselectors.standardcollection.selectors.NumberPropertySelector;
import ru.vladislav117.eentityselectors.utils.DoubleObject;
import ru.vladislav117.eentityselectors.utils.StringUtils;

import java.util.*;

public class NumberPropertySelectorType extends SelectorType {
    protected List<String> propertyNames;
    protected NumberPropertySelector.NumberPropertyGetter propertyGetter;
    protected NumberPropertySelector.NumberPropertyGetter minimalValuePropertyGetter;
    protected NumberPropertySelector.NumberPropertyGetter maximalValuePropertyGetter;

    public NumberPropertySelectorType(String id, Collection<String> propertyNames, NumberPropertySelector.NumberPropertyGetter propertyGetter, NumberPropertySelector.NumberPropertyGetter minimalValuePropertyGetter, NumberPropertySelector.NumberPropertyGetter maximalValuePropertyGetter) {
        super(id);
        this.propertyNames = new ArrayList<>(propertyNames);
        this.propertyGetter = propertyGetter;
        this.minimalValuePropertyGetter = minimalValuePropertyGetter;
        this.maximalValuePropertyGetter = maximalValuePropertyGetter;
    }

    public NumberPropertySelectorType(String id, String propertyName, NumberPropertySelector.NumberPropertyGetter propertyGetter, NumberPropertySelector.NumberPropertyGetter minimalValuePropertyGetter, NumberPropertySelector.NumberPropertyGetter maximalValuePropertyGetter) {
        super(id);
        propertyNames = new ArrayList<>();
        propertyNames.add(propertyName);
        this.propertyGetter = propertyGetter;
        this.minimalValuePropertyGetter = minimalValuePropertyGetter;
        this.maximalValuePropertyGetter = maximalValuePropertyGetter;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    public NumberPropertySelector.NumberPropertyGetter getPropertyGetter() {
        return propertyGetter;
    }

    public NumberPropertySelector.NumberPropertyGetter getMinimalValuePropertyGetter() {
        return minimalValuePropertyGetter;
    }

    public NumberPropertySelector.NumberPropertyGetter getMaximalValuePropertyGetter() {
        return maximalValuePropertyGetter;
    }

    @Override
    public @Nullable Selector read(String query) {
        for (String propertyName : propertyNames) {
            if (query.startsWith(propertyName + "><")) {
                query = StringUtils.removePrefix(query, propertyName + "><");
                DoubleObject<DoubleObject<Double, Double>, Boolean> readResult = readPairOfNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.IN_RANGE, readResult.getSecond(), 0, readResult.getFirst().getFirst(), readResult.getFirst().getSecond(), propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "<>")) {
                query = StringUtils.removePrefix(query, propertyName + "<>");
                DoubleObject<DoubleObject<Double, Double>, Boolean> readResult = readPairOfNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.OUTSIDE_RANGE, readResult.getSecond(), 0, readResult.getFirst().getFirst(), readResult.getFirst().getSecond(), propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "<=")) {
                query = StringUtils.removePrefix(query, propertyName + "<=");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.LESS_OR_EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "<")) {
                query = StringUtils.removePrefix(query, propertyName + "<");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.LESS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + ">=")) {
                query = StringUtils.removePrefix(query, propertyName + ">=");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.GREATER_OR_EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + ">")) {
                query = StringUtils.removePrefix(query, propertyName + ">");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.GREATER, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "==")) {
                query = StringUtils.removePrefix(query, propertyName + "==");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "=")) {
                query = StringUtils.removePrefix(query, propertyName + "=");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "!==")) {
                query = StringUtils.removePrefix(query, propertyName + "!==");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.NOT_EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            } else if (query.startsWith(propertyName + "!=")) {
                query = StringUtils.removePrefix(query, propertyName + "!=");
                DoubleObject<Double, Boolean> readResult = readNumber(query);
                if (readResult == null) return null;
                return new NumberPropertySelector(NumberPropertySelector.NumberCompareOperation.NOT_EQUALS, readResult.getSecond(), readResult.getFirst(), 0, 0, propertyGetter, minimalValuePropertyGetter, maximalValuePropertyGetter);
            }
        }
        return null;
    }

    public @Nullable DoubleObject<Double, Boolean> readNumber(String string) {
        if (string.endsWith("%")) {
            try {
                return new DoubleObject<>(Double.parseDouble(string.replace("%", "")), true);
            } catch (Exception exception) {
                return null;
            }
        }
        try {
            return new DoubleObject<>(Double.parseDouble(string), false);
        } catch (Exception exception) {
            return null;
        }
    }

    public @Nullable DoubleObject<DoubleObject<Double, Double>, Boolean> readPairOfNumber(String string) {
        String[] numbers = string.split(";");
        if (numbers.length != 2) return null;
        DoubleObject<Double, Boolean> first = readNumber(numbers[0]);
        DoubleObject<Double, Boolean> second = readNumber(numbers[1]);
        if (first == null || second == null) return null;
        return new DoubleObject<>(new DoubleObject<>(first.getFirst(), second.getFirst()), first.getSecond() || second.getSecond());
    }
}
