import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//in addlast addfirst if size = maxSize then resize move and call addfirst or addlast again on it after modifying maxsize else size++ and addfirst addlast accordingly
public class ArrayDeque61B<T> implements Deque61B<T> {
    private int maxSize = 8;
    private T[] arrayDeque = (T[]) new Object[maxSize];
    private int front = -1;
    private int frontPillar = -1;
    private int back = maxSize / 2 - 1;
    private int backPillar = back;
    private int size = 0;
    private int helper = 0;

    public ArrayDeque61B() {}

    private void resize() {

    }

    @Override
    public void addFirst(T x) {
        size++;
        if(size == maxSize) {
            resize();
        }
        if(front < back) {
            front++;
            if(front == backPillar + 1) {
                front = maxSize;
            }
        }
        if(front > back) {
            front--;
        }
        arrayDeque[front] = x;
    }

    @Override
    public void addLast(T x) {
        size++;
        if(size == maxSize + 1) {
            resize();
        }
        if(back >= backPillar && helper == 0) {
            back++;
            if(back == maxSize) {
                back = backPillar;
            }
        }
        if(back <= backPillar) {
            back--;
            if(helper == 0) {
                back++;
                helper = 1;
            }
        }
        arrayDeque[back] = x;
    }

    @Override
    public List<T> toList() {
        List<T> arrayList = new ArrayList<>();
        int i = front;
        //Need new condition
        if(front < back && back >= backPillar) {
            while(i > frontPillar && arrayDeque[i] != null) {
                arrayList.add(arrayDeque[i]);
                i--;
            }
            i = backPillar;
            while(i < back) {
                i++;
                arrayList.add(arrayDeque[i]);
            }
        }
        else {
            i = frontPillar + 1;
            while(i != back && i != backPillar + 1) {
                arrayList.add(arrayDeque[i]);
                i++;
            }
            if(i == back) {
                i = backPillar;
                while(i < maxSize - 1) {
                    i++;
                    arrayList.add(arrayDeque[i]);
                }
                i = backPillar;
                while(arrayDeque[i] != null && i >= back) {
                    arrayList.add(arrayDeque[i]);
                    i--;
                }
            }
            else {
                i = maxSize - 1;
                while(arrayDeque[i] != null && i > back) {
                    arrayList.add(arrayDeque[i]);
                    i--;
                }
                i = backPillar + 1;
                while(arrayDeque[i] != null && i <= back) {
                    arrayList.add(arrayDeque[i]);
                    i++;
                }
            }
        }

        return arrayList;
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
        if(size == 0) {
            return null;
        }
        size--;

        T element = null;

        if(front != frontPillar) {
            element = arrayDeque[front];
            arrayDeque[front] = null;
            front--;
        }
        else {
            backPillar++;
            element = arrayDeque[backPillar];
            arrayDeque[backPillar] = null;
        }
        return element;
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
