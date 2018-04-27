/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;
import java.util.*;
/**
 *
 * @author Alex
 */
public class City {
    // attributes
    public String cityName;
    public int[] timeCostArray;
    public int[]  monetaryCostArray;
    
    // methods
    public String getCityName (){
        return this.cityName;
    }
    public void setArraysToZero(Integer sizeLimit){
        // loop through all input data and initialize arrayLists to 0
        this.timeCostArray = new int[sizeLimit];  
        this.monetaryCostArray = new int[sizeLimit];
        
        for (int i = 0; i < sizeLimit; i++) {
            this.timeCostArray[i] = 0;  
            this.monetaryCostArray[i] = 0;            
        }

    }
    public void setTimeCost(int destinationIndex, int timeCostValue){
        this.timeCostArray[destinationIndex] = timeCostValue;
    }
     public void setMonetaryCost(int destinationIndex, int monetaryCostValue){
        this.monetaryCostArray[destinationIndex] = monetaryCostValue;
    }
    // constructors
    City(String inputCityName){
        this.cityName = inputCityName;
    }
    
    City(){
        cityName = null;         
    }   
}
