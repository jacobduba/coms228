package Apr12.Stacks;

public interface PureStack<E> {
    void push (E item);
    E pop();
    E peek();
    boolean isEmpty();
    int size();
}
