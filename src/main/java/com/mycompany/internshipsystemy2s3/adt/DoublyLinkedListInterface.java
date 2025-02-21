package com.mycompany.internshipsystemy2s3.adt;

/**
 * DoublyLinkedListInterface.java
 * Interface for DoublyLinkedList ADT.
 *
 * @param <T> Element type.
 */
public interface DoublyLinkedListInterface<T> extends Iterable<T> {

    /**
     * Adds an element to the end of the list.
     *
     * @param element The element to be added.
     */
    void add(T element);

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index   The index at which the element is to be inserted.
     * @param element The element to be added.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    void add(int index, T element);

    /**
     * Retrieves the element at the specified index in the list.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    T get(int index);

    /**
     * Removes the element at the specified index in the list.
     *
     * @param index The index of the element to be removed.
     * @return The element that was removed.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    T remove(int index);

    /**
     * Removes the first occurrence of the specified element from the list, if present.
     *
     * @param element The element to be removed.
     * @return true if the element was successfully removed, false otherwise.
     */
    boolean remove(T element);

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    int size();

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements, false otherwise.
     */
    boolean isEmpty();

    /**
     * Clears all elements from the list, making it empty.
     */
    void clear();

    /**
     * Returns an iterator over the elements in the list.
     * @return An iterator for this list.
     */
    @Override
    java.util.Iterator<T> iterator();
}