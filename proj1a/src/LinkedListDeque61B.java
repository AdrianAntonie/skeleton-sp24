/** Implementation of a deque using a doubly linked list and all of its common methods */
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel = new Node();
    private int size = 0;
    public LinkedListDeque61B() {

    }

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        /** Constructor for the sentinel Node */
        public Node() {
            next = this;
            prev = this;
        }

        /** Constructor for a regular Node */
        public Node(T data) {
            this.data = data;
        }
    }

    @Override
    public void addFirst(T x) {
        size++;
        Node xNode = new Node(x);
        xNode.next = sentinel.next;
        xNode.prev = sentinel;
        sentinel.next.prev = xNode;
        sentinel.next = xNode;
    }

    @Override
    public void addLast(T x) {
        size++;
        Node xNode = new Node(x);
        sentinel.prev.next = xNode;
        xNode.prev = sentinel.prev;
        xNode.next = sentinel;
        sentinel.prev = xNode;
    }

    @Override
    public List<T> toList() {
        if(size == 0) {
            return List.of();
        }
        List <T> arrayList = new ArrayList<>(size);
        Node pointerNode = sentinel;
        for(int i = 0; i < size; i++) {
            arrayList.add(pointerNode.next.data);
            pointerNode = pointerNode.next;
        }
        return arrayList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size;
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
