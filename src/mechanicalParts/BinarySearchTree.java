package mechanicalParts;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @Author 30007873 Jai Ananda
 * @Version 1.0
 * @Description: Binary Search Tree - Mechanical Parts
 *
 */
public class BinarySearchTree {

	// Generate List of Strings
	public static List<String> getStringsList() {
		List<String> mechanicalParts = new ArrayList<String>();
		// add items to the list
		mechanicalParts.add("Tyres");
		mechanicalParts.add("Brake Pads");
		mechanicalParts.add("Transmission Cable");
		mechanicalParts.add("Suspension Pads");
		mechanicalParts.add("Wiper Blades");
		mechanicalParts.add("Brake Oil Filter");
		mechanicalParts.add("Turbo Engine");
		mechanicalParts.add("Air Conditioning filter");
		mechanicalParts.add("Alloy Wheels");
		mechanicalParts.add("Oxygen Sensor");
		mechanicalParts.add("Engine sensor");

		return mechanicalParts;
	}// End of List Generation

	private Node root; // root of BinarySearchTree

	class Node {
		// string sorted by key
		private String key;
		private Integer value;
		// left and right sub tree
		private Node left, right;
		// number of nodes in subtree
		private int size;

		public Node(String key, Integer value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + ", left=" + left + ", right=" + right + ", size=" + size + "]";
		}
	}


	// Initializes an empty binary search tree.
	public BinarySearchTree() {
	}


	 // This method is used to get value by key, accepts key as argument,
	 // returns value and does not throw any exception

	public Integer get(String key) {
		return get(root, key);
	}

	//This method is used to get value by node and key, accepts node and key as
	//arguments, returns value and throws illegal argument exception when
	//key is null
	private Integer get(Node node, String key) {
		if (key == null)
			throw new IllegalArgumentException();
		if (node == null)
			return null;
		int compare = key.compareTo(node.key);
		if (compare < 0)
			return get(node.left, key);
		else if (compare > 0)
			return get(node.right, key);
		else
			return node.value;
	}

	//This method is used to put (insert or update) value by key and value,
	// accepts key and value as arguments, returns value and throws
	// illegal argument exception when key is null
	public void put(String key, Integer value) {
		if (key == null)
			throw new IllegalArgumentException();
		if (value == null) {
			delete(key);
			return;
		}
		root = put(root, key, value);
	}

	//This method is used to put node and returns node where the input arguments
	//are Node, key and value
	private Node put(Node node, String key, Integer value) {
		if (node == null)
			return new Node(key, value, 1);
		int compare = key.compareTo(node.key);
		if (compare < 0)
			node.left = put(node.left, key, value);
		else if (compare > 0)
			node.right = put(node.right, key, value);
		else
			node.value = value;
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}


	 // This method returns the size of the root node
	public int size() {
		return size(root);
	}

	//This method returns number of key-value pairs in BinarySearchTree at node
	private int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.size;
	}

	// This Method returns the smallest key
	public String min() {
		if (isEmpty())
			throw new NoSuchElementException();
		return min(root).key;
	}

	//This method returns minimum value on left node
	private Node min(Node node) {
		if (node.left == null)
			return node;
		else
			return min(node.left);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	//This method is to Delete smallest key value pair from the binary search tree
	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException();
		root = deleteMin(root);
	}


	private Node deleteMin(Node node) {
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	//This method is to delete the largest key value pair from the binary search tree
	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException();
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	//This method deletes the key associated with binary search tree
	public void delete(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		root = delete(root, key);
	}


	private Node delete(Node node, String key) {
		if (node == null)
			return null;

		int compare = key.compareTo(node.key);
		if (compare < 0)
			node.left = delete(node.left, key);
		else if (compare > 0)
			node.right = delete(node.right, key);
		else {
			if (node.right == null)
				return node.left;
			if (node.left == null)
				return node.right;
			Node t = node;
			node = min(t.right);
			node.right = deleteMin(t.right);
			node.left = t.left;
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	public static void main(String[] args) {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		List<String> mechParts = BinarySearchTree.getStringsList();

		System.out.println("Before sorting: ");
		System.out.println(mechParts);
		System.out.println("\nAfter sorting: ");
		System.out.println(mechParts);

		for (int x = 0; x < mechParts.size(); x++) {
			String key = mechParts.get(x);
			binarySearchTree.put(key, x);
		}

		System.out.println("\nConstructed binary search tree: ");
		System.out.println(binarySearchTree);

		System.out.println("\nEnter a key between 0 to 10");
		Scanner scanner = new Scanner(System.in);
		int key = scanner.nextInt();

		String searchString = mechParts.get(key);
		System.out.println("Searching for: " + searchString);
		System.out.println("Result: " + searchString);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return "BinarySearchTree [root=" + root + "]";
	}
}
