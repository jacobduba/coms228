package Mar28.DoublyLinkedList;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        public E data;
        public Node next;
        public Node previous;
        public Node(E e) {
            data = e;
        }
    }

    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    /**
     * Inserts newNode into this list after current without updating size. O(1)
     */
    private void link(Node current, Node newNode) {
        newNode.previous = current;
        newNode.next = current.next;
        current.next.previous = newNode;
        current.next = newNode;
    }

    /**
     * Removes current without updating size. O(1)
     */
    private void remove(Node current) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
    }

    /**
     * Returns the Node whose index is pos, which will be head is pos == -1
     * and tail if pos == size. O(n)
     */
    private Node findNodeByIndex(int pos) {
        if (pos == -1) return head;
        if (pos == size) return tail;
        Node current = head.next;
        int count = 0;
        while (count < pos) {
            current = current.next;
            count++;
        }
        return current;
    }

    public boolean add(E item) {
        Node temp = new Node(item);
        link(tail.previous, temp);
        ++size;
        return true;
    }

    public void add(int pos, E item) {
        if (pos < 0 || pos > size) // Can be size as you are adding to the end.
            throw new IndexOutOfBoundsException("" + pos);
        Node temp = new Node(item);
        Node predecessor = findNodeByIndex(pos - 1);
        link(predecessor, temp);
        ++size;
    }

    private class DoublyLinkedIterator {
        private static final int BEHIND = -1;
        private static final int AHEAD = 1;
        private static final int NONE = 0;

        private Node cursor;
        private int index;
        private int direction;

        public DoublyLinkedIterator(int pos) {
            if (pos < 0 || pos > size)
                throw new IndexOutOfBoundsException("" + pos);
            cursor = findNodeByIndex(pos);
            index = pos;
            direction = NONE;
        }

        public DoublyLinkedIterator() {
            this(0);
        }

        public void add(E item) {
            Node temp = new Node(item);
            link(cursor.previous, temp);
            ++index;
            ++size;
            direction = NONE;
        }

        public boolean hasNext() {
            return index < size;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E ret = cursor.data;
            cursor = cursor.next;
            ++index;
            direction = BEHIND;
            return ret;
        }

        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            cursor = cursor.previous;
            --index;
            direction = AHEAD;
            return cursor.data;
        }

        public void set(E item) {
            if (direction == NONE)
                throw new IllegalStateException();

            if (direction == AHEAD) {
                cursor.data = item;
            } else {
                cursor.previous.data = item;
            }
        }

        public void remove() {
            if (direction == NONE) {
                throw new IllegalStateException();
            } else {
                if (direction == AHEAD) {
                    Node n = cursor.next;
                    unlink(cursor);
                    cursor = n;
                } else {
                    unlink(cursor.previous);
                    --index;
                }
            }
            --size;
            direction = NONE;
        }
    }

    public DoublyLinkedIterator listIterator() {
        return new DoublyLinkedIterator();
    }

    public DoublyLinkedIterator listIterator(int pos) {
        return new DoublyLinkedIterator(pos);
    }

    private void unlink(Node current) {
        current.next.previous = current.previous;
        current.previous.next = current.next;
    }
}