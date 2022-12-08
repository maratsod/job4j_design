package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
            container[size++] = value;
            modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        container[index] = newValue;
        size++;
        modCount++;
        return container[index];
    }

    @Override
    public T remove(int index) {
        modCount++;
        Objects.checkIndex(index, container.length);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        return container[index];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return size < container.length;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!(size >= container.length)) {
                    throw new NoSuchElementException();
                }
                return container[size++];
            }

        };
    }
}
