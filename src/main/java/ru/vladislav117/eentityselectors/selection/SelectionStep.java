package ru.vladislav117.eentityselectors.selection;

import ru.vladislav117.eentityselectors.operation.Operation;
import ru.vladislav117.eentityselectors.selector.Selector;
import ru.vladislav117.eentityselectors.source.Source;

public class SelectionStep {
    protected Operation operation;
    protected Source source;
    protected Selector selector;

    public SelectionStep(Operation operation, Source source, Selector selector) {
        this.operation = operation;
        this.source = source;
        this.selector = selector;
    }

    public Operation getOperation() {
        return operation;
    }

    public Source getSource() {
        return source;
    }

    public Selector getSelector() {
        return selector;
    }
}
