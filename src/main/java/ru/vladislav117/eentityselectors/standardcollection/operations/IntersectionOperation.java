package ru.vladislav117.eentityselectors.standardcollection.operations;

import org.bukkit.entity.Entity;
import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.selection.Selection;

public class IntersectionOperation extends Operation {
    @Override
    public EntitySet apply(EntitySet entities, EntitySet operand, Selection selection) {
        EntitySet intersectedSet = new EntitySet();
        if (entities.getSize() < operand.getSize()) {
            for (Entity entity : entities.getEntities()) {
                if (operand.contains(entity)) intersectedSet.add(entity);
            }
        } else {
            for (Entity entity : operand.getEntities()) {
                if (entities.contains(entity)) intersectedSet.add(entity);
            }
        }
        return intersectedSet;
    }
}
