package com.mycompany.internshipsystemy2s3.adt;

/**
 * adt/DoublyLinkedList.java
 * DoublyLinkedList ADT implementation.
 */
public class DoublyLinkedList<T> implements DoublyLinkedListInterface<T> {

    /**
     * Node.java - Inner class for list nodes.
     * Holds data, next, and previous node references.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private Node<T> head; // List head node
    private Node<T> tail; // List tail node
    private int size;     // List size

    /** Constructor: Initializes empty list. */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = tail = newNode; // Empty list: new node is head and tail
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;         // Update tail
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            add(element); // Append if index is size
            return;
        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            if (isEmpty()) {
                head = tail = newNode; // Empty list: new node is head and tail
            } else {
                newNode.next = head;
                head.previous = newNode;
                head = newNode;         // Update head
            }
        } else {
            Node<T> currentNode = getNodeAt(index);
            Node<T> previousNode = currentNode.previous;

            newNode.next = currentNode;
            newNode.previous = previousNode;
            currentNode.previous = newNode;
            previousNode.next = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNodeAt(index).data;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> nodeToRemove = getNodeAt(index);
        T removedData = nodeToRemove.data;

        if (size == 1) {
            head = tail = null; // Single element list
        } else if (index == 0) {
            head = head.next;
            head.previous = null;
        } else if (index == size - 1) {
            tail = tail.previous;
            tail.next = null;
        } else {
            Node<T> previousNode = nodeToRemove.previous;
            Node<T> nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }
        size--;
        return removedData;
    }

    @Override
    public boolean remove(T element) {
        Node<T> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                remove(index);
                return true;
            }
            currentNode = currentNode.next;
            index++;
        }
        return false; // Element not found
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    /** Helper: Gets Node at index. */
    private Node<T> getNodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> currentNode;
        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.previous;
            }
        }
        return currentNode;
    }

    /**
     * DoublyLinkedListIterator.java - Inner class for iterator.
     */
    private class DoublyLinkedListIterator implements java.util.Iterator<T> {
        private Node<T> current;

        public DoublyLinkedListIterator() {
            this.current = head; // Start at head
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported.");
        }
    }
}