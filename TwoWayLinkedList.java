package javaDevelop.M04.M04_assignment3;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node head, tail;
    private int size = 0; // Number of elements in the list

    // Inner Node class with both next and previous pointers
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    // Constructor to create an empty list
    public TwoWayLinkedList() {
    }

    // Constructor to create a list from an array of objects
    public TwoWayLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    // Other methods (getFirst, getLast, addFirst, addLast, removeFirst, removeLast, remove, clear, contains, get, indexOf, lastIndexOf, set, iterator) remain the same as in MyLinkedList.

    // Return the number of elements in this list
    @Override
    public int size() {
        return size;
    }

    // Implement the listIterator() method
    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayLinkedListIterator(0);
    }

    // Implement the listIterator(int index) method
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return new TwoWayLinkedListIterator(index);
    }

    // Inner class for ListIterator
    private class TwoWayLinkedListIterator implements ListIterator<E> {
        private Node<E> nextItem;
        private int nextIndex;

        public TwoWayLinkedListIterator(int index) {
            if (index < size / 2) {
                nextItem = head;
                nextIndex = 0;
                while (nextIndex < index) {
                    nextItem = nextItem.next;
                    nextIndex++;
                }
            } else {
                nextItem = tail;
                nextIndex = size - 1;
                while (nextIndex > index) {
                    nextItem = nextItem.previous;
                    nextIndex--;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = nextItem.element;
            nextItem = nextItem.next;
            nextIndex++;
            return result;
        }
    }

    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // Using listIterator to iterate through the list
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
