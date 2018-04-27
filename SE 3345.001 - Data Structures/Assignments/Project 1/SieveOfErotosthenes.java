import java.util.*;
import java.lang.*;

// Project 1
// Alex Lundin
// 09-05-2017
// AML140830  
// SE 3345.001
// Data Structures


public class SieveOfErotosthenes {


	public static void main(String[] args) {
	
         System.out.println ("Welcome to the main driver for Assignment 1. " );
         System.out.println ("Enter a integer, n, to find all primes that are less than or equal to: " );	
         
	
         // scanner for client input
   		Scanner input = new Scanner (System.in);
   		int n = 0;	
         
         // data validation before continueing
         if(input.hasNextInt()){
            n = input.nextInt();
            FindPrimes(n);
         }
         // error handler for invalid data
         else{
            System.out.println("You must enter a integer value, exiting gracefully now to handler error.");
         } 
   		
}



    // static method to hold Sieve
    static void FindPrimes(int n) { 

        
        boolean[] booleanPrimeArray = new boolean[n+1];
        int[] integerArray = new int[n+1];
        
        // by default, booleanPrimeArray to true
        // by default, store all values into parallel integerArray
        for (int i = 2; i <= n; i++) {
            booleanPrimeArray[i] = true;
            integerArray[i] = i;
        }

        // mark non-primes <= n, in booleanPrimeArray, using Sieve of Eratosthenes
        for (int i = 2; i*i <= n; i++) {

            // if i is prime, then continue on by marking multiples of i as nonprime
            if (booleanPrimeArray[i]) {
                for (int j = i; i*j <= n; j++) {
                    booleanPrimeArray[i*j] = false;
                }
            }
        }
        
        System.out.println("Here is the complete list of primes, from 2 up until " + n + ".");
        
        // print primes
        // use the booleanPrimeArray parallel Boolean array to determine if number is prime
        // if number is prime, use integerArray to print value 
        for (int i = 2; i <= n; i++) {
            if (booleanPrimeArray[i]){
            System.out.println(integerArray[i]);
            }
        }
                
    }
}


