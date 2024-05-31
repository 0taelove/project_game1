package org.yeongtae.global.validators;

public interface Validator<T> {
    void check(T form);
}