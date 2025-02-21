package com.mycompany.internshipsystemy2s3.adt;

/**
 * DoublyLinkedList.java
 * Implements DoublyLinkedListInterface and Iterable for enhanced functionality.
 *
 * @param <T> The type of elements stored in the list.
 */
public class DoublyLinkedList<T> implements DoublyLinkedListInterface<T> {

    /**
     * Node - Represents a node in the doubly linked list.
     * Encapsulates data and references to the next and previous nodes.
     * Private and static for optimal encapsulation and efficiency.
     *
     * @param <T> The type of data held by the node.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        /**
         * Constructor for Node.
         *
         * @param data The data to be stored in the node.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private Node<T> head;
    private Node<T> tail; // Pointer to the last node for efficient append.
    private int size;     // Tracks number of elements.

    /**
     * Default constructor for DoublyLinkedList.
     * Creates an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * @inheritDoc
     * @param element Element to add.
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * @inheritDoc
     * @param index   Index to insert at.
     * @param element Element to insert.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            add(element);
            return;
        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.previous = newNode;
                head = newNode;
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

    /**
     * @inheritDoc
     * @param index Index to retrieve.
     * @return Element at index.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNodeAt(index).data;
    }

    /**
     * @inheritDoc
     * @param index Index to remove.
     * @return Removed element.
     * @throws IndexOutOfBoundsException if index is invalid.
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> nodeToRemove = getNodeAt(index);
        T removedData = nodeToRemove.data;

        if (size == 1) {
            head = tail = null;
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

    /**
     * @inheritDoc
     * @param element Element to remove.
     * @return true if removed, false otherwise.
     */
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
        return false;
    }

    /**
     * @inheritDoc
     * @return Number of elements.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @inheritDoc
     * @return true if empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @inheritDoc
     * Clears the list.
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * @inheritDoc
     * @return List iterator.
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    /**
     * Helper method to get the Node at a specific index.
     * Optimizes traversal by starting from head or tail based on index position.
     *
     * @param index Node index.
     * @return Node at index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
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
     * DoublyLinkedListIterator - Inner class for list iteration.
     * Implements the java.util.Iterator interface.
     * Private inner class for encapsulation.
     */
    private class DoublyLinkedListIterator implements java.util.Iterator<T> {
        private Node<T> current;

        /**
         * Constructor for DoublyLinkedListIterator.
         * Starts iteration from the head of the list.
         */
        public DoublyLinkedListIterator() {
            this.current = head;
        }

        /**
         * @inheritDoc
         * @return true if next element exists.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * @inheritDoc
         * @return Next element.
         * @throws java.util.NoSuchElementException if no more elements.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }

        /**
         * @inheritDoc
         * Remove operation is unsupported.
         * @throws UnsupportedOperationException always.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported in this iterator.");
        }
     }
 }