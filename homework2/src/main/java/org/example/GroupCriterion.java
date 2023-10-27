package org.example;


@FunctionalInterface
public interface GroupCriterion<X, T> {
    X getkey(X arg1, T arg2);
}
