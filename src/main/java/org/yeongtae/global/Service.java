package org.yeongtae.global;

public interface Service<T> {
    default void process(T form) {};
    default T process() {
        return null;
    }
}