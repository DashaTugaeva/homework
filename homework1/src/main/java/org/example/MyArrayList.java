package org.example;

public class MyArrayList<T> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10000];
        size = 0;
    }

   //метод add занимает O(1) времени, но когда необходимо создать новый массив и скопировать в него все элементы, то время составляет O(n)
    public void add(T element) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
    }

    private void expandCapacity() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    //здесь время всегда составляет O(1), так как получаем элемент по индексу
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
