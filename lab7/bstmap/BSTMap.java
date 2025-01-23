package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class BSTNode{
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap(){}

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (get(root, key) != null){
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode T = get(root, key);
        if (T == null) {
            return null;
        }
        return T.value;

    }

    private BSTNode get(BSTNode T, K key){
        if (T == null){
            return null;
        }
        int cmp = key.compareTo(T.key);
        if (cmp < 0){
            return get(T.left, key);
        } else if (cmp > 0){
            return get(T.right, key);
        } else {
            return T;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)){
            return;
        }
        this.root = put(root, key, value);

    }

    private BSTNode put(BSTNode T, K key, V value){
        if (T == null){
            this.size += 1;
            return new BSTNode(key, value);
        }

        int cmp = key.compareTo(T.key);
        if (cmp < 0){
            T.left = put(T.left, key, value);
        } else if (cmp > 0) {
            T.right = put(T.right, key, value);
        } else {
            T.value = value;
        }

        return T;
    }

    public String printInOrder(){
        if (root == null){
            return "Empty tree";
        }

        StringBuilder sb = new StringBuilder();
        printInOrder(root, "", sb);
        return sb.toString();
    }

    private void printInOrder(BSTNode T, String prefix, StringBuilder sb){
        if (T == null){
            return;
        }

        printInOrder(T.left, prefix, sb);

        sb.append(prefix)
                        .append(T.key)
                                .append(": ")
                                        .append(T.value)
                                                .append(";\n");


        printInOrder(T.right, prefix, sb);
    }


    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<K>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(BSTNode T, Set<K> set){
        if (T == null){
            return;
        }
        set.add(T.key);
        addKeys(T.left, set);
        addKeys(T.right, set);
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)){
            V targetValue = get(key);
            root = remove(root, key);
            size -= 1;
            return targetValue;
        }
        return null;

    }

    private BSTNode remove(BSTNode T, K key){
        if (T == null){
            return null;
        }

        int cmp = key.compareTo(T.key);
        if (cmp < 0){
            T.left = remove(T.left, key);
        } else if (cmp > 0) {
            T.right = remove(T.right, key);
        } else {
            //Case 1: the node has no child
            if (T.left == null && T.right == null){
                return null;
            }

            //Case 2: the node has one child
            if (T.left == null){
                return T.right;
            }
            if (T.right == null){
                return T.left;
            }

            //Case 3: the node has two children
            BSTNode successor = findMinChild(T.right);
            T.key = successor.key;
            T.value = successor.value;
            T.right = remove(T.right, successor.key);
        }

        return T;

    }

    private BSTNode findMinChild(BSTNode T){
        BSTNode current = T;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }

    @Override
    public V remove(K key, V value) {
        if (containsKey(key)){
            V targetValue = get(key);
            if (targetValue.equals(value)){
                root = remove(root, key);
                size -= 1;
                return targetValue;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

//    public Iterator<K> iterator() {
//        return new BSTMapIterator();
//    }
//
//    private class BSTMapIterator implements Iterator<K>{
//        private int wizPos;
//        private Set<K> setOfKeys;
//
//        public BSTMapIterator(){
//            wizPos = 0;
//            setOfKeys = keySet();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return wizPos < setOfKeys.size();
//        }
//
//        @Override
//        public K next() {
//            Iterator<K> iterator = setOfKeys.iterator();
//            K element = iterator.next();
//            return element;
//        }
//    }

//    public static void main(String[] args){
//        BSTMap bstm = new BSTMap();
//
//        bstm.put("hi", 1);
//        bstm.put("waterYouDoingHere", 0);
//        bstm.put("starChild", 5);
//
//        System.out.println(bstm.printInOrder());
//
//    }
}
