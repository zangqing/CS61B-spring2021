package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author QZ
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

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
    // You should probably define some more!
    private int N = 0; // # of elements in the map
    private final double maxLoadFactor;
    private static final int Default_Initial_Size = 16;
    private static final double Default_Load_Factor = 0.75;

    /** Constructors */
    public MyHashMap() {
        this(Default_Initial_Size, Default_Load_Factor);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, Default_Load_Factor);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        maxLoadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
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
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        N = 0;
        buckets = createTable(Default_Initial_Size);
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    private int getBucketIndex(K key, int numOfBuckets){
        int keyHash = key.hashCode();
        // Use Math.floorMod() instead of modulus % to handle negative hashcode due to overflow
        return Math.floorMod(keyHash, numOfBuckets);
    }

    private Node getNode(K key){
        int bucketIndex = getBucketIndex(key, buckets.length);

        if (buckets[bucketIndex] == null){
            return null;
        }

        for (Node node : buckets[bucketIndex]){
            if (node.key.equals(key)){
                return node;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node != null){
            return node.value;
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        int bucketIndex = getBucketIndex(key, buckets.length);
        if (node != null){
            node.value = value;
            return;
        }

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = createBucket();
        }
        node = new Node(key, value);
        buckets[bucketIndex].add(node);

        N += 1;

        if (reachMaxLoad()){
            resize(buckets.length * 2);
        }

    }

    private boolean reachMaxLoad(){
        return (double) N /buckets.length > maxLoadFactor;
    }

    private void resize(int capacity){
        Collection<Node>[] newBuckets = createTable(capacity);
        Iterator<Node> nodeIterator = new MyHashMapNodeIterator();
        while (nodeIterator.hasNext()){
            Node node = nodeIterator.next();
            int newBucketIndex = getBucketIndex(node.key, newBuckets.length);
            if (newBuckets[newBucketIndex] == null){
                newBuckets[newBucketIndex] = createBucket();
            }
            newBuckets[newBucketIndex].add(node);
        }

//        for (Collection<Node> bucket : buckets) {
//            if (bucket == null) continue;  // skip empty buckets to avoid NullPointerException
//            for (Node node : bucket) {
//                int newBucketIndex = getBucketIndex(node.key, newBuckets.length);
//                if (newBuckets[newBucketIndex] == null) {
//                    newBuckets[newBucketIndex] = createBucket();
//                }
//                newBuckets[newBucketIndex].add(node);
//            }
//        }

        buckets = newBuckets;
    }


    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key, buckets.length);
        Node node = getNode(key);
        //V targetValue = node.value;
        if (node == null){
            return null;
        }
        N -= 1;
        buckets[bucketIndex].remove(node);
        return node.value;
    }

    @Override
    public V remove(K key, V value) {
        int bucketIndex = getBucketIndex(key, buckets.length);
        Node node = getNode(key);
        if (node == null){
            return null;
        }

        // Make sure node is not null so node.value will not result in a NullPointerException
        V targetValue = node.value;
        if (targetValue.equals(value)){
            buckets[bucketIndex].remove(node);
            N -= 1;
            return targetValue;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        //addKeys(buckets, set);
        for (K key : this){
            set.add(key);
        }
        return set;
    }

//    private void addKeys(Collection<Node>[] collections, Set<K> set){
//        for (Collection<Node> collection : collections) {
//            if (collection == null) continue;
//            for (Node node : collection) {
//                set.add(node.key);
//            }
//        }
//
//    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K>{
        private final Iterator<Node> nodeIterator = new MyHashMapNodeIterator();

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public K next() {
            return nodeIterator.next().key;
        }


    }

    private class MyHashMapNodeIterator implements Iterator<Node>{
        private final Iterator<Collection<Node>> bucketsIterator = Arrays.stream(buckets).iterator();
        private Iterator<Node> currentBucketIterator;
        private int remainingNodes = N;

        @Override
        public boolean hasNext() {
            return remainingNodes > 0;
        }

        @Override
        public Node next() {
            if (currentBucketIterator == null || !currentBucketIterator.hasNext()){
                Collection<Node> currentBucket = bucketsIterator.next();
                while (currentBucket == null){
                    currentBucket = bucketsIterator.next();
                }
                currentBucketIterator = currentBucket.iterator();
            }
            remainingNodes -= 1;
            return currentBucketIterator.next();
        }
    }

}
