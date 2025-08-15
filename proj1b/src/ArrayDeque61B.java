import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//in addlast addfirst if size = maxSize then resize move and call addfirst or addlast again on it after modifying maxsize else size++ and addfirst addlast accordingly
public class ArrayDeque61B<T> implements Deque61B<T> {
    private int maxSize = 8;
    private T[] arrayDeque = (T[]) new Object[maxSize];


    public ArrayDeque61B() {}


    //test
    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {

    }

    @Override
    public boolean isEmpty() {

    }

    @Override
    public int size() {
        
    }

    @Override
    public T removeFirst() {

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
