import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//in addlast addfirst if size = maxSize then resize move and call addfirst or addlast again on it after modifying maxsize else size++ and addfirst addlast accordingly
public class ArrayDeque61B<T> implements Deque61B<T> {

    private int size = 0;
    private int maxSize = 8;
    private int firstElementNextIndex = 7;
    private int lastElementNextIndex = 0;
    public T[] arrayDeque =(T[]) new Object[8];

    public ArrayDeque61B() {

    }

    private void resize() {
        maxSize *= 2;
        T[] resizedArrayDeque = (T[]) new Object[maxSize];
        int k = maxSize - 1;
        for(int i = size - 1; i > firstElementNextIndex; i--) {
            resizedArrayDeque[k--] = arrayDeque[i];
        }
        firstElementNextIndex = k;
        for(int i = 0; i < lastElementNextIndex; i++) {
            resizedArrayDeque[i] = arrayDeque[i];
        }
        arrayDeque = resizedArrayDeque;
    }

    @Override
    public void addFirst(T x) {
        if(size != maxSize) {
            size++;
            arrayDeque[firstElementNextIndex] = x;
            firstElementNextIndex--;
        } else {
            resize();
            addFirst(x);
        }
    }

    @Override
    public void addLast(T x) {
        if(size != maxSize) {
            size++;
            arrayDeque[lastElementNextIndex] = x;
            lastElementNextIndex++;
        } else {
            resize();
            addLast(x);
        }
    }

    @Override
    public List<T> toList() {
        if(isEmpty()){
            return List.of();
        }
        ArrayList<T> dequeList = new ArrayList<>(size);
        int k = 0;
        for(int i = firstElementNextIndex + 1; i < maxSize; i++) {
            dequeList.add(k++, arrayDeque[i]);
        }
        for(int i = 0; i < lastElementNextIndex; i++) {
            dequeList.add(k++, arrayDeque[i]);
        }
        return dequeList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
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
