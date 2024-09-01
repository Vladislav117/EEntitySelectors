package ru.vladislav117.eentityselectors.standardcollection.operations;

import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.selection.Selection;

public class AddingOperation extends Operation {
    @Override
    public EntitySet apply(EntitySet entities, EntitySet operand, Selection selection) {
        entities.add(operand);
        return entities;
    }
}
