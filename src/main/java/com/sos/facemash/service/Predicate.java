package com.sos.facemash.service;

public interface Predicate<T> {
    boolean testCondition(T t);
}
