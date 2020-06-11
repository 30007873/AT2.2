package bbst;

import java.util.logging.Logger;

import domain.BBSTNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
/**
 * @Author 30007873 Jai Ananda
 * @Version 1.0
 * @Description: Balanced Binary Search Tree (BBST) - Mechanical Parts
 */
public class BalancedBinarySearchTreeTest {

    private static final Logger log = Logger.getLogger(BalancedBinarySearchTreeTest.class.getName());

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
        log.info("Running JUnit test cases from class: " + BalancedBinarySearchTree.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + BalancedBinarySearchTree.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

    /**
     * This method is used to test 'toString', accepts nothing, returns nothing and does not throw any exception
     */
    @Test
    public void toStringTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 9);
        balancedBinarySearchTree.add("Air Conditioning filter", 7);
        String string = balancedBinarySearchTree.toString().trim();
        assertEquals(string, "BBSTNode{key=Oxygen Sensor, value=9}" + "\n" + " " + "BBSTNode{key=Air Conditioning filter, value=7}");
        reset();
    }

    /**
     * This method is used to test 'addTest', accepts nothing, returns nothing and throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void addTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        balancedBinarySearchTree.add("Air Conditioning filter", 1);
        balancedBinarySearchTree.add("Tyres", 2);
        BBSTNode root = balancedBinarySearchTree.root;

        assertEquals("Oxygen Sensor", root.getKey());
        assertEquals(Integer.valueOf(0), root.getValue());
        assertEquals(3, balancedBinarySearchTree.size());

        assertEquals("Air Conditioning filter", root.getLeft().getKey());
        assertEquals(Integer.valueOf(1), root.getLeft().getValue());

        assertEquals("Tyres", root.getRight().getKey());
        assertEquals(Integer.valueOf(2), root.getRight().getValue());

        balancedBinarySearchTree.add(null, 9);

        reset();
    }

    /**
     * This method is used to test 'remove', accepts nothing, returns nothing and throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void remove() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        balancedBinarySearchTree.add("Air Conditioning filter", 1);
        balancedBinarySearchTree.add("Tyres", 2);

        BBSTNode bbstNode = balancedBinarySearchTree.search("Air Conditioning filter");
        assertEquals(3, balancedBinarySearchTree.size());
        balancedBinarySearchTree.remove(bbstNode);
        assertEquals(2, balancedBinarySearchTree.size());
        bbstNode = balancedBinarySearchTree.search("Air Conditioning filter");
        assertNull(bbstNode);

        balancedBinarySearchTree.remove(null);

        reset();
    }

    /**
     * This method is used to test 'minimumTest', accepts nothing, returns nothing and throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void minimumTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        balancedBinarySearchTree.add("Air Conditioning filter", 1);
        balancedBinarySearchTree.add("Tyres", 2);
        BBSTNode bbstNode = balancedBinarySearchTree.search("Air Conditioning filter");
        BBSTNode bbstNodeMin = balancedBinarySearchTree.minimum(bbstNode);
        assertEquals("Air Conditioning filter", bbstNodeMin.getKey());
        assertEquals(1, bbstNodeMin.getValue());

        balancedBinarySearchTree.minimum(null);

        reset();
    }

    /**
     * This method is used to test 'searchTest', accepts nothing, returns nothing and throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void searchTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        balancedBinarySearchTree.add("Air Conditioning filter", 1);
        balancedBinarySearchTree.add("Tyres", 2);
        BBSTNode bbstNode = balancedBinarySearchTree.search("Air Conditioning filter");
        assertEquals("Air Conditioning filter", bbstNode.getKey());
        assertEquals(1, bbstNode.getValue());

        bbstNode = balancedBinarySearchTree.search("zzz");
        assertNull(bbstNode);

        balancedBinarySearchTree.search(null);

        reset();
    }

    /**
     * This method is used to test 'sizeTest', accepts nothing, returns nothing and does not throw any exception
     */
    @Test
    public void sizeTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        balancedBinarySearchTree.add("Air Conditioning filter", 1);
        balancedBinarySearchTree.add("Tyres", 2);

        assertEquals(3, balancedBinarySearchTree.size());
        balancedBinarySearchTree.root = null;
        assertEquals(0, balancedBinarySearchTree.size());

        reset();
    }

    /**
     * This method is used to test 'isEmptyTest', accepts nothing, returns nothing and does not throw any exception
     */
    @Test
    public void isEmptyTest() {
        BalancedBinarySearchTree balancedBinarySearchTree = new BalancedBinarySearchTree();
        assertTrue(balancedBinarySearchTree.isEmpty());

        balancedBinarySearchTree.add("Oxygen Sensor", 0);
        assertTrue(!balancedBinarySearchTree.isEmpty());

        reset();
    }
}
