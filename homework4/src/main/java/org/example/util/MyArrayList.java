package org.example.util;

import java.lang.reflect.Array;

public class MyArrayList<T> {
    public T[] elements;
    public int size;

    public MyArrayList(Class<T> type) {
        elements = (T[]) Array.newInstance(type, 10000);
        size = 0;
    }

    public void add(T element, Class<T> type) {
        if (size == elements.length) {
            expandCapacity(type);
        }
        elements[size] = element;
        size++;
    }
    public void expandCapacity(Class<T> type) {
        int newCapacity = elements.length * 2;
        T[] newElements = (T[]) Array.newInstance(type, newCapacity);
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return (T) elements[index];
    }
    public int size() {
        return size;
    }
}
