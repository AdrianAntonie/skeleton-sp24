import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;
    private int size = 0;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        if(!containsKey(key)) {
            size++;
        }
        root = put(root, key, value);
    }

    @Override
    public V get(K key) {
        BSTNode result = get(root, key);
        if(result != null) {
            return result.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    private BSTNode put(BSTNode node, K key, V value){
        if(node == null) {
            return new BSTNode(key, value);
        }
        if(node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        }
        else if(node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }
        return node;
    }

    private BSTNode get(BSTNode node, K key) {
        if(node == null) {
            return null;
        }
        if(node.key.equals(key)) {
            return node;
        }
        if(node.key.compareTo(key) > 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

}