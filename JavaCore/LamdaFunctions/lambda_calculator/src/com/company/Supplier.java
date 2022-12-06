package com.company;
@FunctionalInterface
public interface Supplier<T> {
    Calculator get();
}
