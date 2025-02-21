package com.mycompany.internshipsystemy2s3.adt;

/**
 *
 * @author wbrya
 */
public interface ListInterface<T> {
    void add(T newEntry);                       // Adds newEntry to the end of the list
    boolean add(int newPosition, T newEntry);   // Adds newEntry at givenPosition
    T remove(int givenPosition);                // Removes the entry at givenPosition
    void clear();                               // Removes all entries from the list
    boolean replace(int givenPosition, T newEntry); // Replaces the entry at givenPosition with newEntry
    T getEntry(int givenPosition);              // Returns the entry at givenPosition
    boolean contains(T anEntry);                // True if list contains anEntry, false otherwise
    int getNumberOfEntries();                   // Returns the number of entries in the list
    boolean isEmpty();                          // True if list is empty, false otherwise
    boolean isFull();                           // True if list is full, false otherwise
}