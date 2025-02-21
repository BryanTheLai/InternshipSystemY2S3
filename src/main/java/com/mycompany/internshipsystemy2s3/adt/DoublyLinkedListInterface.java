package com.mycompany.internshipsystemy2s3.adt;

/**
 * adt/DoublyLinkedListInterface.java
 * Interface for DoublyLinkedList ADT.
 * @param <T>
 */
public interface DoublyLinkedListInterface<T> {

    /** Adds element to end.
     * @param element */
    void add(T element);

    /** Adds element at index.
     * @param index
     * @param element */
    void add(int index, T element);

    /** Gets element at index.
     * @param index
     * @return  */
    T get(int index);

    /** Removes element at index.
     * @param index
     * @return  */
    T remove(int index);

    /** Removes first occurrence of element.
     * @param element
     * @return  */
    boolean remove(T element);

    /** Returns list size.
     * @return  */
    int size();

    /** Checks if list is empty.
     * @return  */
    boolean isEmpty();

    /** Clears all elements. */
    void clear();

    /** Returns list iterator.
     * @return  */
    java.util.Iterator<T> iterator();
}