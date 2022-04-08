package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 *
 * author: Jacob Duba
 */
public class MultiList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
    /**
     * Default number of elements that may be stored in each node.
     */
    private static final int DEFAULT_NODESIZE = 4;

    /**
     * Number of elements that can be stored in each node.
     */
    private final int nodeSize;

    /**
     * Dummy node for head.  It should be private but set to public here only
     * for grading purpose.  In practice, you should always make the head of a
     * linked list a private instance variable.
     */
    public Node head;

    /**
     * Dummy node for tail.
     */
    private final Node tail;

    /**
     * Number of elements in the list.
     */
    private int size;

    /**
     * Constructs an empty list with the default node size.
     */
    public MultiList() {
        this(DEFAULT_NODESIZE);
    }

    /**
     * Constructs an empty list with the given node size.
     *
     * @param nodeSize number of elements that may be stored in each node, must be
     *                 an even number
     */
    public MultiList(int nodeSize) {
        if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
        // dummy nodes
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
        this.nodeSize = nodeSize;
    }

    /**
     * Constructor for grading only.  Fully implemented.
     *
     * @param head
     * @param tail
     * @param nodeSize
     * @param size
     */
    public MultiList(Node head, Node tail, int nodeSize, int size) {
        this.head = head;
        this.tail = tail;
        this.nodeSize = nodeSize;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public void add(int pos, E item) {
        if (0 > pos || pos > size) throw new IndexOutOfBoundsException();
        if (item == null) throw new NullPointerException();
        // if the list is empty, create a new node and put X at offset 0
        if (size == 0) {
            Node temp = new Node();
            temp.addItem(item);
            link(head, temp);
        } else {
            NodeInfo info = find(pos);

            // Chose to use the same variable names as the doc
            Node n = info.node;
            int off = info.offset;
            final int M = nodeSize;

            // otherwise, if off = 0 and if n has a predecessor which has fewer than M elements (and is not the head), put X in n's predecessor
            if (off == 0 && n.previous != head) {
                if (n.previous.count < M) {
                    n.previous.addItem(item);
                // if n is the tail node and n’s predecessor has M elements, create a new node and put X at offset 0
                } else if (n.previous.count == M && n == tail) {
                    Node temp = new Node();
                    temp.addItem(item);
                    link(tail.previous, temp);
                }
            // otherwise if there is space in node n, put X in node n at offset off, shifting array elements as necessary
            } else if (n.count < M) {
                n.addItem(off, item);
            // otherwise, perform a split operation: move the last M/2 elements of node n into a new successor node n', and then
            } else {
                Node temp = new Node();
                for (int i = M / 2; i < M; i++) {
                    temp.addItem(n.data[M / 2]); // When you remove, the elements go back,
                    n.removeItem(M / 2); // so we need to stay in the same place
                }
                link(n, temp);

                // if off <= M/2, put X in node n at offset off
                if (off <= M / 2) {
                    n.addItem(off, item);
                // if off > M /2, put X in node n' at offset (off − M /2)
                } else {
                    temp.addItem(off - M / 2, item);
                }
            }
        }
        size++;
    }

    @Override
    public E remove(int pos) {
        if (0 > pos || pos >= size) throw new IndexOutOfBoundsException();
        NodeInfo info = find(pos);
        // Chose to use the same variable names as the doc
        Node n = info.node;
        int off = info.offset;
        final int M = nodeSize;

        E item = n.data[off];

        // if the node n containing X is the last node and has only one element, delete it;
        if (n.next == tail && n.count == 1) {
            unlink(n);
        // otherwise, if n is the last node (thus with two or more elements) , or if n has more than elements, remove X
            // from n, shifting elements as necessary
        } else if (n.next == tail || n.count > M / 2) {
            n.removeItem(off);
        // otherwise (the node n must have at most M/2 elements), look at its successor n' (note that we
            // don’t look at the predecessor of n) and perform a merge operation as follows:
        } else {
            n.removeItem(off);
            Node nS = n.next;
            // if the successor node n' has more than M/2 elements, move the first element from n' to n.
            if (nS.count > M / 2) {
                n.addItem(nS.data[0]);
                nS.removeItem(0);
            // if the successor node n' has M / 2 or fewer elements, then move all elements from n' to n and
            // delete n' (full merge)
            } else {
                for (int i = 0; i < nS.count; i++) {
                    n.addItem(nS.data[0]); // When you remove, the elements go back,
                }
                unlink(nS);
            }
        }
        size--;
        return item;
    }

    /**
     * Returns array containing elements of MultiList, while deleting MultiList.
     * This is for the internal use of sort() and sortReverse().
     * @return E array
     */
    private E[] deleteMultiListAndReturnArray() {
        E[] items = (E[]) new Comparable[size];

        int i = 0;
        Node cur = head.next;
        while (cur != tail) {
            for (E item : cur.data) {
                if (item != null) { // The for loops ends up iterating through all parts of an array... even if they're null.
                    items[i] = item;
                    i++; size--;
                }
            }
            unlink(cur);
            cur = cur.next;
        }

        return items;
    }

    /**
     * Sort all elements in the Multi list in NON-DECREASING order. You may do the following.
     * Traverse the list and copy its elements into an array, deleting every visited node along
     * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting
     * efficiency is not a concern for this project.)  Finally, copy all elements from the array
     * back to the Multi list, creating new nodes for storage. After sorting, all nodes but
     * (possibly) the last one must be full of elements.
     * <p>
     * Comparator<E> must have been implemented for calling insertionSort().
     */
    public void sort() {
        E[] items = deleteMultiListAndReturnArray();

        class MultiListComparator implements Comparator<E> {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        }

        insertionSort(items, new MultiListComparator());

        for (E item : items) {
            add(item);
        }
    }

    /**
     * Sort all elements in the Multi list in NON-INCREASING order. Call the bubbleSort()
     * method.  After sorting, all but (possibly) the last nodes must be filled with elements.
     * <p>
     * Comparable<? super E> must be implemented for calling bubbleSort().
     */
    public void sortReverse() {
        E[] items = deleteMultiListAndReturnArray();

        bubbleSort(items);

        for (E item : items) {
            add(item);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MultiListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MultiListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MultiListIterator(index);
    }

    /**
     * Returns a string representation of this list showing
     * the internal structure of the nodes.
     */
    public String toStringInternal() {
        return toStringInternal(null);
    }

    /**
     * Returns a string representation of this list showing the internal
     * structure of the nodes and the position of the iterator.
     *
     * @param iter an iterator for this list
     */
    public String toStringInternal(ListIterator<E> iter) {
        int count = 0;
        int position = -1;
        if (iter != null) {
            position = iter.nextIndex();
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node current = head.next;
        while (current != tail) {
            sb.append('(');
            E data = current.data[0];
            if (data == null) {
                sb.append("-");
            } else {
                if (position == count) {
                    sb.append("| ");
                    position = -1;
                }
                sb.append(data);
                ++count;
            }

            for (int i = 1; i < nodeSize; ++i) {
                sb.append(", ");
                data = current.data[i];
                if (data == null) {
                    sb.append("-");
                } else {
                    if (position == count) {
                        sb.append("| ");
                        position = -1;
                    }
                    sb.append(data);
                    ++count;

                    // iterator at end
                    if (position == size && count == size) {
                        sb.append(" |");
                        position = -1;
                    }
                }
            }
            sb.append(')');
            current = current.next;
            if (current != tail)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Node type for this list.  Each node holds a maximum
     * of nodeSize elements in an array.  Empty slots
     * are null.
     */
    private class Node {
        /**
         * Array of actual data elements.
         */
        // Unchecked warning unavoidable.
        public E[] data = (E[]) new Comparable[nodeSize];

        /**
         * Link to next node.
         */
        public Node next;

        /**
         * Link to previous node;
         */
        public Node previous;

        /**
         * Index of the next available offset in this node, also
         * equal to the number of elements in this node.
         */
        public int count;

        /**
         * Adds an item to this node at the first available offset.
         * Precondition: count < nodeSize
         *
         * @param item element to be added
         */
        void addItem(E item) {
            if (count >= nodeSize) {
                return;
            }
            data[count++] = item;
            //useful for debugging
            //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
        }


        /**
         * Adds an item to this node at the indicated offset, shifting
         * elements to the right as necessary.
         * <p>
         * Precondition: count < nodeSize
         *
         * @param offset array index at which to put the new element
         * @param item   element to be added
         */
        void addItem(int offset, E item) {
            if (count >= nodeSize) {
                return;
            }
            for (int i = count - 1; i >= offset; --i) {
                data[i + 1] = data[i];
            }
            ++count;
            data[offset] = item;
            //useful for debugging
            //      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
        }

        /**
         * Deletes an element from this node at the indicated offset,
         * shifting elements left as necessary.
         * Precondition: 0 <= offset < count
         *
         * @param offset
         */
        void removeItem(int offset) {
            E item = data[offset];
            for (int i = offset + 1; i < nodeSize; ++i) {
                data[i - 1] = data[i];
            }
            data[count - 1] = null;
            --count;
        }
    }

    private class NodeInfo {
        public Node node;
        public int offset;

        public NodeInfo(Node node, int offset) {
            this.node = node;
            this.offset = offset;
        }
    }

    /**
     * Helper method that helps us get the Node a pos is in.
     * @param pos
     * @return NodeInfo object containing Node that pos exists in and it's offset.
     */
    private NodeInfo find(int pos) {
        int tempPos = 0;
        Node cur = head.next;
        while (cur != tail && tempPos + cur.count - 1 < pos) {
            tempPos += cur.count;
            cur = cur.next;
        }
        return new NodeInfo(cur, pos - tempPos);
    }

    /**
     * Links a node into a new node.
     * @param cur node that exists in list
     * @param newNode new node being inserted into the list
     */
    private void link(Node cur, Node newNode) {
        newNode.previous = cur;
        newNode.next = cur.next;
        cur.next.previous = newNode;
        cur.next = newNode;
    }

    /**
     * Unlinks Node from MultiList
     * @param current Node that is going to be unlinked.
     */
    private void unlink(Node current) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
    }

    private class MultiListIterator implements ListIterator<E> {
        // constants you possibly use ...
        private static final int BEHIND = -1;
        private static final int AHEAD = 1;
        private static final int NONE = 0;
        // instance variables ...
        private Node cursor;
        private int index;
        private int direction;

        private int innerIndex;
        /**
         * Default constructor
         */
        public MultiListIterator() {
            this(0);
        }

        /**
         * Constructor finds node at a given position.
         *
         * @param pos
         */
        public MultiListIterator(int pos) {
            if (pos < 0 || pos > size)
                throw new IndexOutOfBoundsException("" + pos);
            NodeInfo info = find(pos);
            cursor = info.node;
            index = pos;
            innerIndex = info.offset;
            direction = NONE;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (cursor == tail) cursor = head.next; // when you create a list iterator it goes to head.next,
                                                        // which in an empty list would be the tail.
            E temp = cursor.data[innerIndex];
            index++; innerIndex++;

            if (cursor.count == innerIndex) {
                cursor = cursor.next;
                innerIndex = 0;
            }

            direction = BEHIND;
            return temp;
        }


        @Override
        public void remove() {
            // TODO catch edge cases
            if (direction == NONE) {
                throw new IllegalStateException();
            } else if (direction == AHEAD) {
                MultiList.this.remove(nextIndex());
            } else if (direction == BEHIND) {
                MultiList.this.remove(previousIndex());
                previous();
            }
            direction = NONE;
        }

        // Other methods you may want to add or override that could possibly facilitate
        // other operations, for instance, addition, access to the previous element, etc.
        //
        // ...
        //

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();


            if (0 == innerIndex) {
                cursor = cursor.previous;
                innerIndex = cursor.count;
            }
            E temp = cursor.data[--innerIndex]; // The iterator index is before array index.
            // So it's like A |B, previous needs to return A so you decrement first then get |A B

            index--;
            direction = AHEAD;
            return temp;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            if (index - 1 < 0)
                throw new NoSuchElementException();
            return index - 1;
        }

        @Override
        public void set(E item) {
            if (direction == NONE)
                throw new IllegalStateException();

            if (direction == AHEAD) {
                if (cursor.count == innerIndex) {
                    cursor.next.data[0] = item;
                } else {
                    cursor.data[nextIndex()] = item;
                }
            } else {
                if (0 == innerIndex) {
                    cursor.previous.data[cursor.previous.count - 1] = item;
                } else {
                    cursor.data[innerIndex - 1] = item;
                }
            }
        }

        @Override
        public void add(E item) {
            MultiList.this.add(index, item);
            next();
        }
    }

    /**
     * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order.
     *
     * @param arr  array storing elements from the list
     * @param comp comparator used in sorting
     */
    private void insertionSort(E[] arr, Comparator<? super E> comp) {
        for (int i = 1; i < arr.length; i++) {
            E temp = arr[i];
            int j = i - 1;
            while (j > -1 && comp.compare(arr[j], temp) > 0) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
     * description of bubble sort please refer to Section 6.1 in the project description.
     * You must use the compareTo() method from an implementation of the Comparable
     * interface by the class E or ? super E.
     *
     * @param arr array holding elements from the list
     */
    private void bubbleSort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1].compareTo(arr[j]) < 0) {
                    E temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}