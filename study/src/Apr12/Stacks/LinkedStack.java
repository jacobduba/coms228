package Apr12.Stacks;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements PureStack<E> {
    private Node top;
    private int size;

    private class Node {
        E data;
        Node next;
    }

    public LinkedStack() {
        top = null;
        size = 0;
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
    public void push(E element) {
        Node toAdd = new Node();
        toAdd.data = element;
        toAdd.next = top;
        top = toAdd;
        size++;
    }

    @Override
    public E pop() {
        if (top == null) throw new NoSuchElementException();
        E returnVal = top.data;
        top = top.next;
        size--;
        return returnVal;
    }

    @Override
    public E peek() {
        if (top == null) throw new NoSuchElementException();
        return top.data;
    }
}