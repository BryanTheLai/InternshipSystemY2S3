package com.mycompany.internshipsystemy2s3.adt;
/**
 *
 * @author wbrya
 */
// src/main/java/adt/ArrayList.java
public class ArrayList<T> implements ListInterface<T> {
    private T[] items;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    @SuppressWarnings("unchecked") // Type safety ensured by design of ArrayList
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T newEntry) {
        ensureCapacity();
        items[size] = newEntry;
        size++;
    }

    @Override
    public boolean add(int index, T newEntry) { 
        if (index < 0 || index > size) {
            return false;
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = newEntry;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked") // Type safety ensured by design of ArrayList
    private void ensureCapacity() {
        if (size == items.length) {
            int newCapacity = items.length * 2;
            T[] newItems = (T[]) new Object[newCapacity];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }


    @Override
    public T remove(int index) { 
        if (index < 0 || index >= size) { 
            return null;
        }
        T removedEntry = items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
        size--;
        return removedEntry;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean replace(int index, T newEntry) { 
        if (index < 0 || index >= size) {
            return false;
        }
        items[index] = newEntry;
        return true;
    }

    @Override
    public T getEntry(int index) { 
        if (index < 0 || index >= size) {
            return null;
        }
        return items[index];
    }

    @Override
    public boolean contains(T anEntry) {
        for (int i = 0; i < size; i++) {
            if (java.util.Objects.equals(anEntry, items[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
