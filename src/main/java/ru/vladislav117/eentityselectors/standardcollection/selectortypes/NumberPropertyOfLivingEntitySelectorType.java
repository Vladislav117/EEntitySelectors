package ru.vladislav117.eentityselectors.standardcollection.selectortypes;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class NumberPropertyOfLivingEntitySelectorType extends NumberPropertySelectorType {
    public NumberPropertyOfLivingEntitySelectorType(String id, Collection<String> propertyNames, NumberPropertyOfLivingEntityGetter propertyGetter, NumberPropertyOfLivingEntityGetter minimalValuePropertyGetter, NumberPropertyOfLivingEntityGetter maximalValuePropertyGetter) {
        super(id, propertyNames, entity -> {
            if (entity instanceof LivingEntity livingEntity) return propertyGetter.getProperty(livingEntity);
            return null;
        }, entity -> {
            if (entity instanceof LivingEntity livingEntity)
                return minimalValuePropertyGetter.getProperty(livingEntity);
            return null;
        }, entity -> {
            if (entity instanceof LivingEntity livingEntity)
                return maximalValuePropertyGetter.getProperty(livingEntity);
            return null;
        });
    }

    public NumberPropertyOfLivingEntitySelectorType(String id, String propertyName, NumberPropertyOfLivingEntityGetter propertyGetter, NumberPropertyOfLivingEntityGetter minimalValuePropertyGetter, NumberPropertyOfLivingEntityGetter maximalValuePropertyGetter) {
        super(id, propertyName, entity -> {
            if (entity instanceof LivingEntity livingEntity) return propertyGetter.getProperty(livingEntity);
            return null;
        }, entity -> {
            if (entity instanceof LivingEntity livingEntity)
                return minimalValuePropertyGetter.getProperty(livingEntity);
            return null;
        }, entity -> {
            if (entity instanceof LivingEntity livingEntity)
                return maximalValuePropertyGetter.getProperty(livingEntity);
            return null;
        });
    }

    @FunctionalInterface
    public interface NumberPropertyOfLivingEntityGetter {
        @Nullable Double getProperty(LivingEntity livingEntity);
    }
}
