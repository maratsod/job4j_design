package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, head);
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
        modCount++;
    }

    public boolean revert() {
        boolean rsl;
        if (head == null || head.next == null) {
            rsl = false;
        } else {
            Node<T> tempPrev = null;
            Node<T> temp = head;
            Node<T> tempNext;
            while (temp != null) {
                tempNext = temp.next;
                temp.next = tempPrev;
                tempPrev = temp;
                temp = tempNext;
            }
            head = tempPrev;
            rsl = true;
        }
        return rsl;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        T temp = node.item;
        int count = 0;
        while (count < size) {
            if (count == index) {
                temp = node.item;
            }
            count++;
            node = node.next;
        }
        return temp;
    }

    public T deleteFirst() {
        T temp;
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> node = head.next;
            temp = head.item;
            head.next = null;
            head.item = null;
            head = node;
        }
        return temp;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!(hasNext())) {
                    throw new NoSuchElementException();
                }
                T temp = node.item;
                node = node.next;
                return temp;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}