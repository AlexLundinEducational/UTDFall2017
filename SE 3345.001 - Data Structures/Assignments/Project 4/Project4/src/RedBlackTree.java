import java.util.*;
import java.lang.*;
import java.math.*;

// Alex Lundin
// Class: Data Structures and Introduction to Algorithmic Analysis
// Section: SE 3345.001
// Semester: Fall 2017 
// Project 5
// Description:

/**
 *
 * @author Alex
 */
public class RedBlackTree <E extends Comparable<E>>{
    //E element;
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<E> root;

    // NOTE do NOT define any constructors for this project -- the compiler will
    //      generate a default (public, no arguments) constructor for you
    
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
    private void rotateLeft(Node n){
            System.out.print("RotateLeft"+ n);
    }
    private void rotateRight(Node n){
            System.out.print("RotateRight" + n);
    }    


    
   private void adjustAfterInsertion(Node n) {
        // Step 1: color the node red
        setColor(n, RED);
   
        // Step 2: Correct double red problems, if they exist
        if (n != null && n != root && isRed(parentOf(n))) {
            System.out.print("\nAdjust");
            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (isRed(siblingOf(parentOf(n)))) {
                System.out.print("\nAdjust");
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
                System.out.print("\nRight left");
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
                System.out.print("\nLeft right");
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
   
    
   private void rbInsertFixup(Node z){
       
       while (colorOf(parentOf(z)) == RED){
           System.out.print("\nInsertFixup");
           if(parentOf(z) == leftOf(grandparentOf(z))){
              Node y = rightOf(grandparentOf(z));
              if(colorOf(y) == RED){
                // case 1
                setColor(parentOf(z), BLACK);
                setColor(y, BLACK);
                setColor(grandparentOf(z), RED);
                z.setElement((grandparentOf(z).getElement()));
              }
              else if(z == (leftOf(parentOf(z)))){
                  //case 2
                  z.setElement((parentOf(z).getElement()));
                  rotateLeft(z);
                  //case 3
                  setColor(parentOf(z), BLACK);
                  setColor(grandparentOf(z), RED);
                  rotateRight(grandparentOf(z));
              }
               
           }
           setColor(root, BLACK);
       }
       
   }
   
    public boolean insert(E element) throws NullPointerException
    {
        boolean isInserted = false;
        
        Node newNode = new Node(element);
        newNode.leftChild = null;
        newNode.rightChild = null;   
        newNode.color = RED;     
        if (this.root == null) {
            this.root = newNode;
        }
          
        Node n = root;
        while (true) {
            //int comparisonResult = compare(element, n.getElement());
            int comparisonResult = 0;
            comparisonResult = n.element.compareTo(element);
            if (comparisonResult == 0) {
                n.setElement(element);
                isInserted = true;
                System.out.print("\nRoot insert");
                return isInserted;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft(newNode);
                    adjustAfterInsertion((Node) rightOf(n));
                    //rbInsertFixup(rightOf(n));
                    isInserted = true;
                    System.out.print("\nRight Insert");
                    break;
                }
                n = n.getLeft();
            } else { // comparisonResult > 0
                if (n.getRight() == null) {
                    n.setRight(newNode);
                    adjustAfterInsertion((Node) leftOf(n));
                    //rbInsertFixup(leftOf(n));
                    isInserted = true;
                    System.out.print("\nLeft Insert");
                    break;
                }
                n = n.getRight();
            }
        }
        
        return isInserted;
        // TODO your code goes here
    }

    public boolean contains(Comparable<E> object)
    {
        boolean isContained = false;
        
        return isContained;
    }

    @Override
    public String toString()
    {
        String returnString = "a";
        printInOrder(this.root);
        return returnString;
    }
    
    public void printInOrder(Node root){
        if(root!=null){
        printInOrder(root.leftChild);
        System.out.print(" " + root.element);

        // print * for deleted Nodes
        if(isRed(root) == RED){
        System.out.print("*");
        }

        printInOrder(root.rightChild);
        }        
    }
            
    // TODO add any helper methods here (optional)

    static class Node <E extends Comparable<E>>
    {
        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;
        boolean color;
        
        /**
         * 
         */
        Node (E element){
            this.element = element;
        }
        
        /**
         * setters
         */
        public void setElement(E element){
                this.element = element;
        }        
        public void setLeft(Node n){
                this.leftChild = n;
        }
        public void setRight(Node n){
                this.rightChild = n;
        }
        public void setParent(Node n){
                this.parent = n;
        }
        
        public void setColor(boolean c){
            // handles null pointer exception
            if (this != null)
                this.color = c;
        }      
        
        /**
         * getters
         */
        public E getElement(){
                E localElement = this.element;
                return localElement;
        } 
        public Node getLeft(){
                Node n = this.leftChild;
                return n;
        }
        public Node getRight(){
                Node n = this.rightChild;
                return n;
        }
        public Node getParent(){
                Node n = this.parent;
                return n;            
        }      
    }
 
}
