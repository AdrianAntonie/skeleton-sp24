package deque;

/** Implementation of a deque using a doubly linked list and all of its common methods */
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel = new Node();
    private int size = 0;
    private Node recursivePtr = sentinel;
    private Node recursivePtr1;
    public LinkedListDeque61B() {

    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("{");
        List<T> list = toList();
        int i;
        for(i = 0; i < list.size() - 1; i++) {
            str.append(list.get(i));
            str.append(", ");
        }
        i++;
        str.append(list.get(i));
        str.append("}");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LinkedListDeque61B deque2) {
            if(this == deque2) {
                return true;
            }
            List<T> list1 = toList();
            List<T> list2 = deque2.toList();
            for(int i = 0; i < list1.size(); i++) {
                if(list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private class LinkedListIterator implements Iterator<T> {
        Node position = sentinel.next;
        public boolean hasNext() {
            return position != sentinel;
        }
        public T next() {
            Node returnNode = position;
            position = position.next;
            return returnNode.data;
        }
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
        if(size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T removedData;
        if(size == 0) {
            return null;
        }
        removedData = sentinel.next.data;
        if(size >= 2) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
        } else {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        }
        size--;
        return removedData;
    }

    @Override
    public T removeLast() {
        T removedData;
        if(size == 0) {
            return null;
        }
        removedData = sentinel.prev.data;
        if(size >= 2){
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
        } else {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        }
        size--;
        return removedData;
    }

    @Override
    public T get(int index) {
        Node pointer = sentinel;
        if(index < 0 || index >= size){
            return null;
        }
        while(index >= 0) {
            pointer = pointer.next;
            index--;
        }
        return pointer.data;
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        if(index == 0) {
            recursivePtr1 = recursivePtr;
            recursivePtr = sentinel;
            return recursivePtr1.next.data;
        }
        recursivePtr = recursivePtr.next;
        return getRecursive(index - 1);
    }
}

