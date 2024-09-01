package ru.vladislav117.eentityselectors.standardcollection.selectors;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;
import ru.vladislav117.eentityselectors.selection.Selection;
import ru.vladislav117.eentityselectors.selector.Selector;

public class NumberPropertySelector extends Selector {
    protected NumberCompareOperation compareOperation;
    protected boolean percentageMode;
    protected double unaryValue;
    protected double binaryFirstValue;
    protected double binarySecondValue;
    protected NumberPropertyGetter propertyGetter;
    protected NumberPropertyGetter minimalValuePropertyGetter;
    protected NumberPropertyGetter maximalValuePropertyGetter;

    public NumberPropertySelector(NumberCompareOperation compareOperation, boolean percentageMode, double unaryValue, double binaryFirstValue, double binarySecondValue, NumberPropertyGetter propertyGetter, NumberPropertyGetter minimalValuePropertyGetter, NumberPropertyGetter maximalValuePropertyGetter) {
        this.compareOperation = compareOperation;
        this.percentageMode = percentageMode;
        this.unaryValue = unaryValue;
        this.binaryFirstValue = Math.min(binaryFirstValue, binarySecondValue);
        this.binarySecondValue = Math.max(binaryFirstValue, binarySecondValue);
        ;
        this.propertyGetter = propertyGetter;
        this.minimalValuePropertyGetter = minimalValuePropertyGetter;
        this.maximalValuePropertyGetter = maximalValuePropertyGetter;
    }

    public NumberCompareOperation getCompareOperation() {
        return compareOperation;
    }

    public boolean isPercentageMode() {
        return percentageMode;
    }

    public double getUnaryValue() {
        return unaryValue;
    }

    public double getBinaryFirstValue() {
        return binaryFirstValue;
    }

    public double getBinarySecondValue() {
        return binarySecondValue;
    }

    public NumberPropertyGetter getPropertyGetter() {
        return propertyGetter;
    }

    public NumberPropertyGetter getMinimalValuePropertyGetter() {
        return minimalValuePropertyGetter;
    }

    public NumberPropertyGetter getMaximalValuePropertyGetter() {
        return maximalValuePropertyGetter;
    }

    @Override
    public @Nullable Boolean isSuitable(Entity entity, Selection selection) {
        Double propertyValue = propertyGetter.getProperty(entity);
        if (propertyValue == null) return false;

        if (percentageMode) {
            Double min = minimalValuePropertyGetter.getProperty(entity);
            Double max = maximalValuePropertyGetter.getProperty(entity);
            if (min == null || max == null) return false;
            propertyValue = (propertyValue - min) / (max - min) * 100;
        }

        if (compareOperation == NumberCompareOperation.LESS) return propertyValue < unaryValue;
        if (compareOperation == NumberCompareOperation.LESS_OR_EQUALS) return propertyValue <= unaryValue;
        if (compareOperation == NumberCompareOperation.GREATER) return propertyValue > unaryValue;
        if (compareOperation == NumberCompareOperation.GREATER_OR_EQUALS) return propertyValue >= unaryValue;
        if (compareOperation == NumberCompareOperation.EQUALS) return propertyValue == unaryValue;
        if (compareOperation == NumberCompareOperation.NOT_EQUALS) return propertyValue != unaryValue;
        if (compareOperation == NumberCompareOperation.IN_RANGE)
            return binaryFirstValue <= propertyValue && propertyValue <= binarySecondValue;
        if (compareOperation == NumberCompareOperation.OUTSIDE_RANGE)
            return propertyValue < binaryFirstValue || binarySecondValue < propertyValue;
        return false;
    }

    @FunctionalInterface
    public interface NumberPropertyGetter {
        @Nullable Double getProperty(Entity entity);
    }

    public enum NumberCompareOperation {
        LESS,
        LESS_OR_EQUALS,
        GREATER,
        GREATER_OR_EQUALS,
        EQUALS,
        NOT_EQUALS,
        IN_RANGE,
        OUTSIDE_RANGE
    }
}
