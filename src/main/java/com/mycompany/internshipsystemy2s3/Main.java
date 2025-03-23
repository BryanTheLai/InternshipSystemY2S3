package com.mycompany.internshipsystemy2s3;

import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;

/**
 * Demonstrates DoublyLinkedList operations.
 */
public class Main {
    public static void main(String[] args) {
        ListInterface<String> myList = new DoublyLinkedList<>();

        System.out.println("Is empty? " + myList.isEmpty());

        myList.add("First");
        myList.add("Second");
        myList.add("Third");

        System.out.println("Is empty? " + myList.isEmpty());
        System.out.println("Size: " + myList.size());
        System.out.println("Index 0: " + myList.get(0));
        System.out.println("Index 1: " + myList.get(1));

        myList.add(1, "Inserted");
        System.out.println("List after insert at 1:");
        for (String item : myList) {
            System.out.println(item);
        }

        String removedElementIndex = myList.remove(2);
        System.out.println("Removed at 2: " + removedElementIndex);
        System.out.println("List after remove at 2:");
        for (String item : myList) {
            System.out.println(item);
        }

        boolean removedElementValue = myList.remove("First");
        System.out.println("Was 'First' removed? " + removedElementValue);
        System.out.println("List after remove 'First':");
        for (String item : myList) {
            System.out.println(item);
        }

        boolean removedNonExistent = myList.remove("NonExistent");
        System.out.println("Removed 'NonExistent'? " + removedNonExistent);

        System.out.println("Size after removals: " + myList.size());

        myList.clear();
        System.out.println("List cleared.");

        System.out.println("Is empty after clear? " + myList.isEmpty());
        System.out.println("Size after clear: " + myList.size());
    }
}