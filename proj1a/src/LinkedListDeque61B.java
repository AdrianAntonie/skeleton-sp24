import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel = new Node();
    public LinkedListDeque61B() {

    }

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node() {
            next = this;
            prev = this;
        }

        /*Constructor for a regular node*/
        public Node(T data) {
            this.data = data;
        }
    }

    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
