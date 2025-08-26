package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void put(K key, V value) {
        size++;
        if((double) size / capacity > loadFactor) {
            resize();
        }
        Node node = new Node(key, value);
        int nodeLocation = getBucket(node.key);
        for(Node nodey : buckets[nodeLocation]) {
            if(nodey.key.equals(node.key)) {
                nodey.value = value;
                size--;
                return;
            }
        }
        buckets[nodeLocation].add(node);
    }

    private void resize() {
        capacity *= 2;
        Collection<Node>[] newBuckets = new Collection[capacity];
        for(int i = 0; i < capacity; i++) {
            newBuckets[i] = createBucket();
        }
        for(int i = 0; i < buckets.length; i++) {
            for(Node node : buckets[i]) {
                newBuckets[getBucket(node.key)].add(node);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public V get(K key) {
        int location = getBucket(key);
        for(Node node : buckets[location]) {
            if(node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int location = getBucket(key);
        for(Node node : buckets[location]) {
            if(node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        for(Collection<Node> bucket : buckets) {
            if(bucket != null) {
                bucket.clear();
            }
        }
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

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size = 0;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[capacity];
        for(int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        buckets = new Collection[capacity];
        for(int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[capacity];
        for(int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    private int getBucket(K key) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
