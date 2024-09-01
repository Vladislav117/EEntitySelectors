package ru.vladislav117.eentityselectors.utils;

public class DoubleObject<T1, T2> {
    protected T1 first;
    protected T2 second;

    public DoubleObject(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }
}
