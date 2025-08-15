import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int maxSize = 8;
    private T[] arrayDeque = (T[]) new Object[maxSize];
    private int front = 0;
    private int size = 0;


    public ArrayDeque61B() {}


    @Override
    public void addFirst(T x) {
        size++;
        if(size > maxSize) {
            resize();
        }
        front = (front - 1 + maxSize) % maxSize;
        arrayDeque[front] = x;
    }

    @Override
    public void addLast(T x) {
        size++;
        if(size > maxSize) {
            resize();
        }
        arrayDeque[(front + size - 1) % maxSize] = x;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        int fakeFront = front;
        if(isEmpty()) {
            return list;
        }
        for(int i = 0; i < size; i++) {
            list.add(arrayDeque[fakeFront]);
            fakeFront = (fakeFront + 1) % maxSize;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        size--;
        T element = arrayDeque[front];
        front = (front + 1) % maxSize;
        return element;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        size--;
        return arrayDeque[(front + size) % maxSize];
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    private void resize() {
        maxSize *= 2;
        T[] newArray = (T[]) new Object[maxSize];
        for(int i = 0; i < size - 1; i++) {
            newArray[i] = arrayDeque[front];
            front = (front + 1) % (size - 1);
        }
        arrayDeque = newArray;
        front = 0;
    }
}
