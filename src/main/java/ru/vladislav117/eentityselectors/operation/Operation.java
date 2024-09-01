package ru.vladislav117.eentityselectors.operation;

import ru.vladislav117.eentityselectors.entity.EntitySet;
import ru.vladislav117.eentityselectors.selection.Selection;

public abstract class Operation {
    public abstract EntitySet apply(EntitySet entities, EntitySet operand, Selection selection);
}
