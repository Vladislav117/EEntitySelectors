package ru.vladislav117.eentityselectors.selection;

public enum SelectionStatus {
    OK(false),
    OPERATION_ERROR(true),
    SOURCE_ERROR(true),
    SELECTOR_ERROR(true),
    OTHER_ERROR(true);

    private final boolean error;

    SelectionStatus(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }
}
