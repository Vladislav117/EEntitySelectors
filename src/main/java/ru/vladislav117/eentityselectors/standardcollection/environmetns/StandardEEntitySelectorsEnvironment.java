package ru.vladislav117.eentityselectors.standardcollection.environmetns;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ru.vladislav117.eentityselectors.environment.EEntitySelectorsEnvironment;
import ru.vladislav117.eentityselectors.standardcollection.operations.AddingOperation;
import ru.vladislav117.eentityselectors.standardcollection.operations.IntersectionOperation;
import ru.vladislav117.eentityselectors.standardcollection.operations.RemovingOperation;
import ru.vladislav117.eentityselectors.standardcollection.operationtypes.SimplePrefixOperationType;
import ru.vladislav117.eentityselectors.standardcollection.selectors.AnyEntitySelector;
import ru.vladislav117.eentityselectors.standardcollection.selectors.LivingEntitySelector;
import ru.vladislav117.eentityselectors.standardcollection.selectors.PlayerSelector;
import ru.vladislav117.eentityselectors.standardcollection.selectortypes.*;
import ru.vladislav117.eentityselectors.standardcollection.sources.AllWorldsSource;
import ru.vladislav117.eentityselectors.standardcollection.sources.InitiatorWorldSource;
import ru.vladislav117.eentityselectors.standardcollection.sources.SelectionSource;
import ru.vladislav117.eentityselectors.standardcollection.sourcetypes.SimplePrefixSourceType;

import java.util.List;

public class StandardEEntitySelectorsEnvironment extends EEntitySelectorsEnvironment {
    public StandardEEntitySelectorsEnvironment() {
        add(new SimplePrefixOperationType("adding", "+", new AddingOperation()));
        add(new SimplePrefixOperationType("removing", "-", new RemovingOperation()));
        add(new SimplePrefixOperationType("intersection", "&", new IntersectionOperation()));
        setDefaultOperation(new AddingOperation());

        add(new SimplePrefixSourceType("all_worlds", "@", new AllWorldsSource()));
        add(new SimplePrefixSourceType("initiator_world", "#", new InitiatorWorldSource()));
        add(new SimplePrefixSourceType("selection", "/", new SelectionSource()));
        setDefaultSource(new AllWorldsSource());

        add(new SimpleWordSelectorType("any", List.of("e", "any"), new AnyEntitySelector()));
        add(new SimpleWordSelectorType("living_entities", List.of("le", "living"), new LivingEntitySelector()));
        add(new SimpleWordSelectorType("players", List.of("player", "players"), new PlayerSelector()));
        add(new StringPropertySelectorType("entity_type", "type", entity -> entity.getType().toString().toLowerCase()).setCaseSensitive(false));
        add(new StringPropertySelectorType("entity_name", "name", CommandSender::getName).setCaseSensitive(false));
        add(new NumberPropertySelectorType("entity_x", "x", entity -> entity.getLocation().getX(), entity -> -entity.getWorld().getWorldBorder().getSize() / 2, entity -> entity.getWorld().getWorldBorder().getSize() / 2));
        add(new NumberPropertySelectorType("entity_y", "y", entity -> entity.getLocation().getY(), entity -> (double) entity.getWorld().getMinHeight(), entity -> (double) entity.getWorld().getMaxHeight()));
        add(new NumberPropertySelectorType("entity_z", "z", entity -> entity.getLocation().getX(), entity -> -entity.getWorld().getWorldBorder().getSize() / 2, entity -> entity.getWorld().getWorldBorder().getSize() / 2));
        add(new NumberPropertyOfLivingEntitySelectorType("entity_health", "health", Damageable::getHealth, livingEntity -> 0.0, livingEntity -> {
            AttributeInstance attributeInstance = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (attributeInstance == null) return null;
            return attributeInstance.getValue();
        }));
        add(new NumberPropertySelectorType("entity_fire_ticks", "fire", entity -> (double) entity.getFireTicks(), entity -> 0.0, entity -> (double) entity.getMaxFireTicks()));
        add(new NumberPropertySelectorType("entity_freeze_ticks", "freeze", entity -> (double) entity.getFreezeTicks(), entity -> 0.0, entity -> (double) entity.getMaxFreezeTicks()));
        add(new NumberPropertySelectorType("entity_passengers", "passengers", entity -> (double) entity.getPassengers().size(), entity -> 0.0, entity -> 2.0));
        setDefaultSelectorType(new PlayerNameSelectorType("player_name"));
    }
}
