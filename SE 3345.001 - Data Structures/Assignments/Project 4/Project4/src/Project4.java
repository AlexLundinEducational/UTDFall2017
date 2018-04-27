import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
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
public class Project4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        RedBlackTree<Integer> tree = new RedBlackTree<>();
//
//        for (int i = 10; i > 0; --i) {
//            tree.insert(i);
//        }

//        RedBlackTree2<String, Integer> tree2 = new RedBlackTree2<String, Integer>();
//        for (int i = 1; i < 9; ++i) {
//            String key = String.valueOf(i);
//            tree2.put(key, i);
//        }       
//        
//        System.out.println(tree2.toString()); 



        RedBlackTree3 tree3 = new RedBlackTree3();

        for (int i = 10; i > 0; --i) {
            tree3.add(i);
        }
          

    }
    
}
