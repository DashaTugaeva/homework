package org.example;

@FunctionalInterface
public interface GroupCriterion<X, T> {
    X getKey(X arg1, T arg2);
}
