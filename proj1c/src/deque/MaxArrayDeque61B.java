package deque;
import java.util.Comparator;
import java.util.List;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    public Comparator<T> c;
    public MaxArrayDeque61B(Comparator<T> c) {
        this.c = c;
    }

    public T max() {
        if(isEmpty()) {
            return null;
        }
        List<T> l = toList();
        l.sort(c);
        return l.get(l.size() - 1);
    }

    public T max(Comparator<T> c) {
        if(isEmpty()) {
            return null;
        }
        List<T> l = toList();
        l.sort(c);
        return l.get(l.size() - 1);
    }

}
