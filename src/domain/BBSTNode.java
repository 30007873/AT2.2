package domain;
/**
 * @Author 30007873 Jai Ananda
 * @Version 1.0
 * @Description: Balanced Binary Search Tree (BBST) - Mechanical Parts
 */
public class BBSTNode<K extends Comparable<K>, V> {
    private BBSTNode left, right;
    private BBSTNode precursor;
    private K key;
    private V value;

    public BBSTNode(K key, V value) {
        this.key = key;
        this.value = value;
        precursor = left = right = null;
    }

    public BBSTNode getLeft() {
        return left;
    }

    public void setLeft(BBSTNode left) {
        this.left = left;
    }

    public BBSTNode getRight() {
        return right;
    }

    public void setRight(BBSTNode right) {
        this.right = right;
    }

    public BBSTNode getPrecursor() {
        return precursor;
    }

    public void setPrecursor(BBSTNode precursor) {
        this.precursor = precursor;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BBSTNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}