/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class Division {
        /**
        Method accepts two arguments, x and y, then divides x by y and returns the result
        * @author Alex Lundin 
        * @param x double variable for numerator of division
        * @param y double variable for denominator of division
        * @return result of x divided by y, value is not stored in method
        * @exception IllegalArgumentException if y equals 0
        */
        public static double division(double x, double y) {
                if(y == 0) { throw new IllegalArgumentException("y is 0"); }
                return x/y;
        }
} 
