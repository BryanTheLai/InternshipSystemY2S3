/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.internshipsystemy2s3;

/**
 *
 * @author wbrya
 */
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedList;
import com.mycompany.internshipsystemy2s3.adt.DoublyLinkedListInterface;

// src/main/java/Main.java

import com.mycompany.internshipsystemy2s3.adt.ArrayList;
import com.mycompany.internshipsystemy2s3.adt.ListInterface;

public class Main {
    public static void main(String[] args) {
        ListInterface<String> list = new ArrayList<>();

        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("add end 'A':");
        list.add("A");
        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("size: " + list.getNumberOfEntries());
        System.out.println("add pos 1 'B': " + list.add(1, "B")); 
        System.out.println("size: " + list.getNumberOfEntries());
        System.out.println("getEntry 0: " + list.getEntry(0)); 
        System.out.println("getEntry 1: " + list.getEntry(1)); 
        System.out.println("replace 0 'C': " + list.replace(0, "C")); 
        System.out.println("getEntry 0: " + list.getEntry(0)); 
        System.out.println("contains 'C': " + list.contains("C"));
        System.out.println("contains 'A': " + list.contains("A"));
        System.out.println("remove 1: " + list.remove(1)); 
        System.out.println("size: " + list.getNumberOfEntries());
        System.out.println("clear:");
        list.clear();
        System.out.println("isEmpty: " + list.isEmpty());
        System.out.println("isFull: " + list.isFull());        
    }
}