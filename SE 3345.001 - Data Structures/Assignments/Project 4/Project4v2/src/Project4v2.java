/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class Project4v2 {

        // Test program
    public static void main( String [ ] args )
    {
//        RedBlackTree<Integer> t = new RedBlackTree<>( );
//        final int NUMS = 400000;
//        final int GAP  =  35461;
//
//        System.out.println( "Checking... (no more output means success)" );
//
//        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
//            t.insert( i );
//        
//        
//        
//        if( t.findMin( ) != 1 || t.findMax( ) != NUMS - 1 )
//            System.out.println( "FindMin or FindMax error!" );
//
//        for( int i = 1; i < NUMS; i++ )
//             if( !t.contains( i ) )
//                 System.out.println( "Find error1!" );
        
        RedBlackTree<Integer> t = new RedBlackTree<>( );
        for( int i = 0; i < 10; ++i)
            t.insert( i );        
       t.printTree(t.header);
    }
    
}
