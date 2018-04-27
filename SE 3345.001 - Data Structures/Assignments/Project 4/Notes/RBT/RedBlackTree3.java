/******************************************************************************
 *  Compilation:  javac RedBlackTree2.java
 *  Execution:    java RedBlackTree2 < input.txt
 *  Dependencies: StdIn.java StdOut.java  
 *  Data files:   https://algs4.cs.princeton.edu/33balanced/tinyST.txt  
 *    
 *  A symbol table implemented using a left-leaning red-black BST.
 *  This is the 2-3 version.
 *
 *  Note: commented out assertions because DrJava now enables assertions
 *        by default.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *  
 *  % java RedBlackTree2 < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 ******************************************************************************/

import java.util.NoSuchElementException;

/**
 *  The {@code BST} class represents an ordered symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides ordered methods for finding the <em>minimum</em>,
 *  <em>maximum</em>, <em>floor</em>, and <em>ceiling</em>.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be {@code null}—setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a left-leaning red-black BST. It requires that
 *  the key type implements the {@code Comparable} interface and calls the
 *  {@code compareTo()} and method to compare two keys. It does not call either
 *  {@code equals()} or {@code hashCode()}.
 *  The <em>put</em>, <em>contains</em>, <em>remove</em>, <em>minimum</em>,
 *  <em>maximum</em>, <em>ceiling</em>, and <em>floor</em> operations each take
 *  logarithmic time in the worst case, if the tree becomes unbalanced.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/33balanced">Section 3.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For other implementations of the same API, see {@link ST}, {@link BinarySearchST},
 *  {@link SequentialSearchST}, {@link BST},
 *  {@link SeparateChainingHashST}, {@link LinearProbingHashST}, and {@link AVLTreeST}.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.util.Comparator;
public class RedBlackTree3 extends BinarySearchTree {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
        @Override
    public String toString(Node node)
    {
        String returnString = "a";
        //traverseInorder();
        return returnString;
    }
    

    public RedBlackTree3() {
        this(null);
    }

    /**
     * Constructs an empty RedBlackTree that orders its items according to the
     * given comparator.
     */
    public RedBlackTree3(Comparator c) {
        super(c);
    }

    /**
     * The nodes in a red-black tree store a color together with the actual data
     * in the node.
     */
    class Node extends LinkedBinaryTreeNode {
        boolean color;

        public Node(Object data) {
            super(data);
        }
    }

    /**
     * Adds a single data item to the tree. If there is already an item in the
     * tree that compares equal to the item being inserted, it is "overwritten"
     * by the new item. Overrides BinarySearchTree.add because the tree needs to
     * be adjusted after insertion.
     */
    public void add(Object data) {
        if (root == null) {
            root = new Node(data);
        }
        BinaryTreeNode n = root;
        while (true) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                n.setData(data);
                return;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft(new Node(data));
                    adjustAfterInsertion((Node) n.getLeft());
                    break;
                }
                n = n.getLeft();
            } else { // comparisonResult > 0
                if (n.getRight() == null) {
                    n.setRight(new Node(data));
                    adjustAfterInsertion((Node) n.getRight());
                    break;
                }
                n = n.getRight();
            }
        }
    }

    /**
     * Removes the node containing the given value. Does nothing if there is no
     * such node.
     */
    public void remove(Object data) {
        Node node = (Node) nodeContaining(data);
        if (node == null) {
            // No such object, do nothing.
            return;
        } else if (node.getLeft() != null && node.getRight() != null) {
            // Node has two children, Copy predecessor data in.
            BinaryTreeNode predecessor = predecessor(node);
            node.setData(predecessor.getData());
            node = (Node) predecessor;
        }
        // At this point node has zero or one child
        Node pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
        if (pullUp != null) {
            // Splice out node, and adjust if pullUp is a double black.
            if (node == root) {
                setRoot(pullUp);
            } else if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(pullUp);
            } else {
                node.getParent().setRight(pullUp);
            }
            if (isBlack(node)) {
                adjustAfterRemoval(pullUp);
            }
        } else if (node == root) {
            // Nothing to pull up when deleting a root means we emptied the tree
            setRoot(null);
        } else {
            // The node being deleted acts as a double black sentinel
            if (isBlack(node)) {
                adjustAfterRemoval(node);
            }
            node.removeFromParent();
        }
    }

    /**
     * Classic algorithm for fixing up a tree after inserting a node.
     */
    private void adjustAfterInsertion(Node n) {
        // Step 1: color the node red
        setColor(n, RED);

        // Step 2: Correct double red problems, if they exist
        if (n != null && n != root && isRed(parentOf(n))) {

            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), BLACK);
                setColor(siblingOf(parentOf(n)), BLACK);
                setColor(grandparentOf(n), RED);
                adjustAfterInsertion(grandparentOf(n));
            }

            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), BLACK);
                setColor(grandparentOf(n), RED);
                rotateRight(grandparentOf(n));
            }

            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), BLACK);
                setColor(grandparentOf(n), RED);
                rotateLeft(grandparentOf(n));
            }
        }

        // Step 3: Color the root black
        setColor((Node) root, BLACK);
    }

    /**
     * Classic algorithm for fixing up a tree after removing a node; the
     * parameter to this method is the node that was pulled up to where the
     * removed node was.
     */
    private void adjustAfterRemoval(Node n) {
        while (n != root && isBlack(n)) {
            if (n == leftOf(parentOf(n))) {
                // Pulled up node is a left child
                Node sibling = rightOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(n), RED);
                    rotateLeft(parentOf(n));
                    sibling = rightOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, RED);
                    n = parentOf(n);
                } else {
                    if (isBlack(rightOf(sibling))) {
                        setColor(leftOf(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateRight(sibling);
                        sibling = rightOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), BLACK);
                    setColor(rightOf(sibling), BLACK);
                    rotateLeft(parentOf(n));
                    n = (Node) root;
                }
            } else {
                // pulled up node is a right child
                Node sibling = leftOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, BLACK);
                    setColor(parentOf(n), RED);
                    rotateRight(parentOf(n));
                    sibling = leftOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, RED);
                    n = parentOf(n);
                } else {
                    if (isBlack(leftOf(sibling))) {
                        setColor(rightOf(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateLeft(sibling);
                        sibling = leftOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), BLACK);
                    setColor(leftOf(sibling), BLACK);
                    rotateRight(parentOf(n));
                    n = (Node) root;
                }
            }
        }
        setColor(n, BLACK);
    }

    // The following helpers dramatically simplify the code by getting
    // all the null pointer checking out of the adjustment methods.

    private boolean colorOf(Node n) {
        return n == null ? BLACK : n.color;
    }

    private boolean isRed(Node n) {
        return n != null && colorOf(n) == RED;
    }

    private boolean isBlack(Node n) {
        return n == null || colorOf(n) == BLACK;
    }

    private void setColor(Node n, boolean c) {
        if (n != null)
            n.color = c;
    }

    private Node parentOf(Node n) {
        return n == null ? null : (Node) n.getParent();
    }

    private Node grandparentOf(Node n) {
        return (n == null || n.getParent() == null) ? null : (Node) n
                .getParent().getParent();
    }

    private Node siblingOf(Node n) {
        return (n == null || n.getParent() == null) ? null : (n == n
                .getParent().getLeft()) ? (Node) n.getParent().getRight()
                : (Node) n.getParent().getLeft();
    }

    private Node leftOf(Node n) {
        return n == null ? null : (Node) n.getLeft();
    }

    private Node rightOf(Node n) {
        return n == null ? null : (Node) n.getRight();
    }
}


