// Alex Lundin
// Class: Data Structures and Introduction to Algorithmic Analysis
// Section: SE 3345.001
// Semester: Fall 2017 
// Project 4
// Description: Red Black Tree


public class RedBlackTree<E extends Comparable<E>>
{
    
    /**
     * Nested Node class
     */
    public static class Node<E>
    {
        /**
         * Node attributes
         */        
        E       element;            // The data in the node
        Node<E> leftChild;          // Left child
        Node<E> rightChild;         // Right child
        Node<E> parent;             // parent
        boolean color;              // Color
        
        /**
         * Node constructors
         */
        Node( E argumentElement )
        {
            this( argumentElement, null, null );
        }

        Node( E argumentElement, Node<E> left, Node<E> right )
        {
            element         = argumentElement;
            leftChild       = left;
            rightChild      = right;
            color           = RedBlackTree.BLACK;
        }


    }
    
    /**
     * RedBlackTree attributes
     */
    private Node<E> root;
    private Node<E> nullNode;
    
    private static final boolean BLACK = false;    // BLACK must be false
    private static final boolean RED   = true;
	
    /**
     * RedBlackTree attributes - used in insert routine and its helpers
     */
	 
    private Node<E> current;
    private Node<E> parent;
    private Node<E> grand;
    private Node<E> great;
    
    /**
     * RedBlackTree Constructor
     */
    public RedBlackTree( )
    {
        nullNode = new Node<>( null );
        nullNode.leftChild = nullNode.rightChild = nullNode;
        root     = new Node<>( null );
        root.leftChild = root.rightChild = nullNode;
    }
	
    /**
     * Insert into the tree.
     * @param element the element to insert.
     */
    public boolean insert( E element ) throws NullPointerException
    {
        boolean isInserted = false;
        current = parent = grand = root;
        nullNode.element = element;

        while( compare( element, current ) != 0 )
        {
            great = grand; grand = parent; parent = current;
            current = compare( element, current ) < 0 ?
                         current.leftChild : current.rightChild;

                // Check if two red children; fix if so
            if( current.leftChild.color == RED && current.rightChild.color == RED )
                 startRotation( element );
        }

            // Insertion fails if already present
        if( current != nullNode )
            return isInserted;
        
        isInserted = true;
        current = new Node<>( element, nullNode, nullNode );

            // Attach to parent
        if( compare( element, parent ) < 0 )
            parent.leftChild = current;
        else
            parent.rightChild = current;
        startRotation( element );
        return isInserted;
    }



    /**
     * Find an item in the tree.
     * @param object the item to search for.
     * @return true if object is found; otherwise false.
     */
    public boolean contains( E object )
    {
        nullNode.element = object;
        current = root.rightChild;

        for( ; ; )
        {
            if( object.compareTo( current.element ) < 0 )
                current = current.leftChild;
            else if( object.compareTo( current.element ) > 0 ) 
                current = current.rightChild;
            else if( current != nullNode )
                return true;
            else
                return false;
        }
    }	
	
    /**
     * overridden toString
     * Returns string of entire tree with in order traversal
     */  
	 
    @Override
    public String toString(){
        String traversalConcatentation = null;
        
         if( isEmpty( ) )
            traversalConcatentation = "Empty tree" ;
        else
            traversalConcatentation = stringJoiner(this.root);     
         
        return traversalConcatentation;
    }
    
    /**
     * Return Node's value as a string
     * Helper method for toString
     * @param t the node that roots the subtree.
     */  
    public String NodetoString(Node<E> t){
        String stringNode = "";
        E element = t.element;
        if( t != nullNode )
        {
            if(t.color == RED){
                stringNode = "*";
            }
            if(element != null){
                stringNode = stringNode + String.valueOf(element) + "\t";
            }
            
            
        }
        return stringNode;
        
    }
    
	
    /**
     * Internal method to join strings created from node and subtrees in sorted order.
     * @param t the node that roots the subtree.
     */
    public String stringJoiner( Node<E> t )
    {
        String stringReturn = "";
        String stringRoot = "";
        String stringLeft = "";
        String stringRight = "";
        
        if( t != nullNode )
        {
            stringLeft = stringJoiner(t.leftChild);           
            stringRoot = NodetoString(t);
            stringRight = stringJoiner(t.rightChild);
            
        }
        
        stringReturn = stringLeft + stringRoot + stringRight;

        
        return stringReturn;
    }
    /**
     * Compare item and t.element, using compareTo, with
     * caveat that if t is header, then item is always larger.
     * This routine is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    public int compare(E item, Node<E> t )
    {
        if( t == root)
            return 1;
        else
            return item.compareTo( t.element );    
    }
    

     
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root.rightChild == nullNode;
    }

    /**
     * Called during an insertion
     * if a node has two red children. Performs flip and rotations.
     * @param item the item being inserted.
     */
    public void startRotation( E item )
    {
            // repaint nodes
        current.color = RED;
        current.leftChild.color = BLACK;
        current.rightChild.color = BLACK;

        if( parent.color == RED )   // Have to rotate
        {
            grand.color = RED;
            if( ( compare( item, grand ) < 0 ) !=
                ( compare( item, parent ) < 0 ) )
                parent = rotateSelection( item, grand );  // Start double rotate
            current = rotateSelection( item, great );
            current.color = BLACK;
        }
        root.rightChild.color = BLACK; // Make root black
    }

    /**
     * select between single or double rotation.
     * 4 cases, LL, LR, RL, RR
     * Called by startRotation.
     * @param item the item in startRotation.
     * @param parent the parent of the root of the rotated subtree.
     * @return the root of the rotated subtree.
     */
    public Node<E> rotateSelection( E item, Node<E> parent )
    {
        if( compare( item, parent ) < 0 )
            return parent.leftChild = compare( item, parent.leftChild ) < 0 ?
                rotateWithLeftChild( parent.leftChild )  :  // LL
                rotateWithRightChild( parent.leftChild ) ;  // LR
        else
            return parent.rightChild = compare( item, parent.rightChild ) < 0 ?
                rotateWithLeftChild( parent.rightChild ) :  // RL
                rotateWithRightChild( parent.rightChild );  // RR
    }

    /**
     * Rotate node with leftChild child.
     */
    public Node<E> rotateWithLeftChild( Node<E> k2 )
    {
        Node<E> k1 = k2.leftChild;
        k2.leftChild = k1.rightChild;
        k1.rightChild = k2;
        return k1;
    }

    /**
     * Rotate node with rightChild child.
     */
    public Node<E> rotateWithRightChild( Node<E> k1 )
    {
        Node<E> k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        k2.leftChild = k1;
        return k2;
    }



}
