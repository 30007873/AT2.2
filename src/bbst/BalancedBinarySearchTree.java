package bbst;

import domain.BBSTNode;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @Author 30007873 Jai Ananda
 * @Version 1.0
 * @Description: Balanced Binary Search Tree (BBST) - Mechanical Parts
 */
public class BalancedBinarySearchTree {

    private static final Logger log = Logger.getLogger(BalancedBinarySearchTree.class.getName());

    protected BBSTNode root;

    /**
     * Constructor for a BBST. Makes an empty BBST.
     */
    public BalancedBinarySearchTree() {
        root = null;
    }

    /**
     * To string
     * @return
     */
    public String toString() {
        if (root == null)
            return "";
        else
            return displayNode(root, 0);
    }

    /**
     * Add spaces
     *
     * @param space
     * @return
     */
    private String setSpaces(int space) {
        String retVal = "";
        for (int i = 0; i < space; i++)
            retVal += " ";
        return retVal;
    }

    /**
     * Return a mechanical part representing the subtree at a bbstNode.
     *
     * @param bbstNode
     * @param depth
     * @return
     */
    private String displayNode(BBSTNode bbstNode, int depth) {
        if (bbstNode == null)
            return "";
        else
            return displayNode(bbstNode.getRight(), depth + 1) + setSpaces(depth) + bbstNode.toString() + "\n"
                    + displayNode(bbstNode.getLeft(), depth + 1);
    }

    /**
     * Add a new bbstNode
     *
     * @param key
     * @param value
     * @return
     */
    public BBSTNode add(String key, int value) {
        BBSTNode newNode = new BBSTNode(key, value);
        BBSTNode bbstNode = root;
        BBSTNode xPrecursor = null;

        while (bbstNode != null) {
            xPrecursor = bbstNode;
            if (key.compareTo((String) bbstNode.getKey()) < 0)
                bbstNode = bbstNode.getLeft();
            else
                bbstNode = bbstNode.getRight();
        }

        newNode.setPrecursor(xPrecursor);

        if (xPrecursor == null)
            root = newNode;
        else {
            if (key.compareTo((String) xPrecursor.getKey()) < 0)
                xPrecursor.setLeft(newNode);
            else
                xPrecursor.setRight(newNode);
        }

        return newNode;
    }

    /**
     * Swap nodes
     *
     * @param fromNode
     * @param toNode
     */
    protected void swap(BBSTNode fromNode, BBSTNode toNode) {
        if (fromNode.getPrecursor() == null)
            root = toNode;
        else if (fromNode == fromNode.getPrecursor().getLeft())
            fromNode.getPrecursor().setLeft(toNode);
        else
            fromNode.getPrecursor().setRight(toNode);

        if (toNode != null)
            toNode.setPrecursor(fromNode.getPrecursor());
    }

    /**
     * Remove a bbstNode newNode
     *
     * @param newNode
     */
    public void remove(BBSTNode newNode) {
        if (newNode.getLeft() == null)
            swap(newNode, newNode.getRight());
        else if (newNode.getRight() == null)
            swap(newNode, newNode.getLeft());
        else {
            BBSTNode rightSubTree = minimum(newNode.getRight());
            if (rightSubTree.getPrecursor() != newNode) {
                swap(rightSubTree, rightSubTree.getRight());
                rightSubTree.setRight(newNode.getRight());
                rightSubTree.getRight().setPrecursor(rightSubTree);
            }
            swap(newNode, rightSubTree);
            rightSubTree.setLeft(newNode.getLeft());
            rightSubTree.getLeft().setPrecursor(rightSubTree);
        }
    }

    /**
     * Return a reference to the bbstNode in the subtree rooted at bbstNode with the minimum key
     *
     * @param bbstNode
     * @return
     */
    public BBSTNode minimum(BBSTNode bbstNode) {
        while (bbstNode.getLeft() != null)
            bbstNode = bbstNode.getLeft();

        return bbstNode;
    }

    /**
     * Search for a bbstNode using a search key.
     *
     * @param searchKey
     * @return
     */
    public BBSTNode search(String searchKey) {
        BBSTNode bbstNode = root;

        while (bbstNode != null && searchKey.compareTo((String) bbstNode.getKey()) != 0) {
            if (searchKey.compareTo((String) bbstNode.getKey()) < 0)
                bbstNode = bbstNode.getLeft();
            else
                bbstNode = bbstNode.getRight();
        }

        if (bbstNode == null)
            return null;
        else
            return bbstNode;
    }

    /**
     * Return BBST size
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(BBSTNode bbstNode) {
        if (bbstNode == null)
            return 0;
        else
            return (size(bbstNode.getLeft()) + 1 + size(bbstNode.getRight()));
    }

    /**
     * Check if BBST is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    public static void main(String[] args) {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Tyres", 0);
        balancedBinarySearchTree.add("Brake Pads", 1);
        balancedBinarySearchTree.add("Transmission Cable", 2);
        balancedBinarySearchTree.add("Suspension Pads", 3);
        balancedBinarySearchTree.add("Wiper Blades", 4);
        balancedBinarySearchTree.add("Brake Oil Filter", 5);
        balancedBinarySearchTree.add("Turbo Engine", 6);
        balancedBinarySearchTree.add("Air Conditioning filter", 7);
        balancedBinarySearchTree.add("Alloy Wheels", 8);
        balancedBinarySearchTree.add("Oxygen Sensor", 9);
        balancedBinarySearchTree.add("Engine sensor", 10);

        System.out.println("\nConstructed binary search tree: ");
        System.out.println(balancedBinarySearchTree.toString());

        System.out.println("Select 1 to add: ");
        System.out.println("Select 2 to delete: ");
        System.out.println("Select 3 to search: ");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        switch(selection){
            case 1: {
                System.out.println("Enter a mechanical part to add: ");
                scanner = new Scanner(System.in);
                String key = scanner.nextLine();
                if(key.isEmpty()){
                    System.out.println("No mechanical part entered.");
                } else {
                    int value = balancedBinarySearchTree.size();
                    balancedBinarySearchTree.add(key, value);
                    System.out.println("\nConstructed binary search tree after adding a new node: ");
                    System.out.println(balancedBinarySearchTree.toString());
                }
                break;
            }

            case 2: {
                System.out.println("Enter a mechanical part to delete: ");
                scanner = new Scanner(System.in);
                String searchKey = scanner.nextLine();
                BBSTNode resultNode = balancedBinarySearchTree.search(searchKey);
                if(resultNode == null){
                    System.out.println("The entered value does not correspond to any mechanical parts in the BBST.");
                } else {
                    balancedBinarySearchTree.remove(resultNode);
                    System.out.println("\nConstructed binary search tree after deleting a node: ");
                    System.out.println(balancedBinarySearchTree.toString());
                }

                break;
            }

            case 3: {
                System.out.println("\nEnter a mechanical part to search: ");
                scanner = new Scanner(System.in);
                String searchKey = scanner.nextLine();
                System.out.println("Searching for: " + searchKey);
                if (balancedBinarySearchTree.search(searchKey) != null) {
                    BBSTNode resultNode = balancedBinarySearchTree.search(searchKey);
                    System.out.println("Search results: ");
                    System.out.println("Result bbstNode: " + resultNode);
                    System.out.println("Result bbstNode key: " + resultNode.getKey());
                    System.out.println("Result bbstNode value: " + resultNode.getValue());
                } else {
                    System.out.println("BBSTNode not found!");
                }
                break;
            }
        }
    }
}
