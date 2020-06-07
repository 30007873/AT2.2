package mechanicalParts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import mechanicalParts.BinarySearchTree.Node;

public class BinarySearchTreeTest {
    private static final Logger log = Logger.getLogger(BinarySearchTreeTest.class.getName());

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        log.info("Setting up...");

    }

    @After
    public void tearDown() {
        log.info("Tearing down...");

    }

    @BeforeClass
    public static void before() {
        log.info("Running JUnit test cases from class: " + BinarySearchTreeTest.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + BinarySearchTreeTest.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

    @Test
    public void getStringsListTest() {
        List<String> list = BinarySearchTree.getStringsList();
        assertEquals(11, list.size()); // test for size; can avoid check for null list;
        reset();
    }

    // @Ignore to avoid testing
    @Test(expected = IllegalArgumentException.class)
    public void getTest() {
        // Testing public Integer get(String key)
        // private Integer get(Node node, String key) cannot be tested using JUnit because
        // it is private;
        // requires Mockito and Powermock to successfully test private Integer get(Node
        // node, String key)
        // constructing a binary tree
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 9);
        binarySearchTree.put("Air Conditioning filter", 7);
        binarySearchTree.put("Tyres", 0);

        System.out.println("\nConstructed binary search tree: ");
        System.out.println(binarySearchTree);
        // test get method
        assertEquals(Integer.valueOf(0), binarySearchTree.get("Tyres"));
        assertEquals(Integer.valueOf(9), binarySearchTree.get("Oxygen Sensor"));
        assertEquals(Integer.valueOf(7), binarySearchTree.get("Air Conditioning filter"));

        try {
            binarySearchTree.get(null);
        } catch (Exception e) {

            throw new IllegalArgumentException();
        }

        reset();
    }

    @Test(expected = IllegalArgumentException.class)
    public void putTest() {
        // Testing public Integer public void put(String key, Integer val)
        // private Node put(Node node, String key, Integer val) cannot be tested using
        // JUnit because
        // it is private;
        // requires Mockito and Powermock to successfully test private Integer get(Node
        // node, String key)

        // constructing a small binary tree
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", 2);
        Node root = binarySearchTree.getRoot();

        assertEquals("Oxygen Sensor", root.getKey());
        assertEquals(Integer.valueOf(0), root.getValue());
        assertEquals(3, root.getSize());

        assertEquals("Air Conditioning filter", root.getLeft().getKey());
        assertEquals(Integer.valueOf(1), root.getLeft().getValue());

        assertEquals("Tyres", root.getRight().getKey());
        assertEquals(Integer.valueOf(2), root.getRight().getValue());

        try {
            binarySearchTree.put(null, 9);
        } catch (Exception e) {

            throw new IllegalArgumentException();
        }

        reset();
    }

    @Test
    public void sizeTest() {
        // constructing a small binary tree
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", 2);

        assertEquals(3, binarySearchTree.size());
        binarySearchTree.setRoot(null);
        assertEquals(0, binarySearchTree.size());
        reset();
    }

    @Test(expected = NoSuchElementException.class)
    public void minTest() {
        // constructing a small binary tree
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        try {
            binarySearchTree.min();
        } catch (Exception e) {

            throw new NoSuchElementException();
        }

        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", null);
        binarySearchTree.put("Tyres", 2);
        assertEquals("Oxygen Sensor", binarySearchTree.min());

        binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", 2);
        assertEquals("Air Conditioning filter", binarySearchTree.min());
    }

    @Test
    public void isEmptyTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        assertTrue(binarySearchTree.isEmpty());
        binarySearchTree.put("Oxygen Sensor", 0);
        assertTrue(!binarySearchTree.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteMinTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", 2);

        binarySearchTree.deleteMin();

        Node root = binarySearchTree.getRoot();
        assertEquals(2, root.getSize());

        binarySearchTree = new BinarySearchTree();
        try {
            binarySearchTree.deleteMin();
        } catch (Exception e) {

            throw new NoSuchElementException();
        }

        binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Tyres", 2);
        binarySearchTree.put("Air Conditioning filter", null);
        binarySearchTree.deleteMin();

        root = binarySearchTree.getRoot();
        assertEquals("Tyres", root.getKey());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteMaxTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", 2);

        binarySearchTree.deleteMax();

        Node root = binarySearchTree.getRoot();
        assertEquals(2, root.getSize());

        binarySearchTree = new BinarySearchTree();
        try {
            binarySearchTree.deleteMax();
        } catch (Exception e) {

            throw new NoSuchElementException();
        }

        binarySearchTree = new BinarySearchTree();
        binarySearchTree.put("Oxygen Sensor", 0);
        binarySearchTree.put("Air Conditioning filter", 1);
        binarySearchTree.put("Tyres", null);
        binarySearchTree.deleteMax();

        root = binarySearchTree.getRoot();
        assertEquals("Air Conditioning filter", root.getKey());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BinarySearchTreeTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
