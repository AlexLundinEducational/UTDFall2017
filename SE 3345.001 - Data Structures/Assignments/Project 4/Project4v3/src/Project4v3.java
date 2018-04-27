// Alex Lundin
// Class: Data Structures and Introduction to Algorithmic Analysis
// Section: SE 3345.001
// Semester: Fall 2017 
// Project 4
// Description: Red Black Tree

public class Project4v3 {

        // Test program
    public static void main( String [ ] args )
    {
        RedBlackTree<Integer> t2 = new RedBlackTree<>( );
        RedBlackTree<Integer> t = new RedBlackTree<>( );
        for( int i = 0; i < 10; ++i)
            t.insert( i );        
       String returnString = t.toString();
       
       System.out.println( returnString );
       boolean result = t.contains(50);
       System.out.println( "\nResult of contains " + result );
    }
    
}



