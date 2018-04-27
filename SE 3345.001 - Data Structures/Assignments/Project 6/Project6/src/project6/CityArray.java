/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;
import static java.lang.Boolean.*;
import java.util.*;
/**
 *
 * @author Alex
 */
public class CityArray {
    
    static ArrayList<City> CITIES;
    static Integer NUMBEROFCITIES;
    
    public void cityCreator(ArrayList<String> inputData){
        String buffer = null;
        String originCity = null;
        String destinationCity = null;
        int monetaryCost = 0;
        int timeCost = 0;  
        
        // loop through all input data and Create cities by name
        for (int i = 0; i < inputData.size(); i++) {
            buffer = inputData.get(i);

            // after first read
            if (i != 0) {
                // split String data by the delimeter |
                String[] tokens = buffer.split("\\|"); 
                originCity = tokens[0];
                destinationCity = tokens[1];
                monetaryCost = Integer.parseInt(tokens[2]);
                timeCost = Integer.parseInt(tokens[3]);   
                
                // check if city Already exists in Array
                Integer doesCityExists = 0;
                doesCityExists = isCityCreated(originCity);
                
                if(doesCityExists == 0){
                     
                    City myCity = new City(originCity);
                    CITIES.add(myCity);
                    NUMBEROFCITIES = NUMBEROFCITIES + 1;
                }    
            }
        }
        
        // loop through all cities
        // intialize Arrays to correct size at set values to zero
        for (int i = 0; i < CITIES.size(); i++) {
            
            // intialize to zero
            CITIES.get(i).setArraysToZero(CITIES.size());
                

        } 
        
        // loop through all input data
        // intialize Arrays to correct size at set values to zero
        // finally add costs to city objects
        for (int i = 0; i < inputData.size(); i++) {
            buffer = inputData.get(i);

            // after first read
            if (i != 0) {
                // split String data by the delimeter |
                String[] tokens = buffer.split("\\|"); 
                originCity = tokens[0];
                destinationCity = tokens[1];
                monetaryCost = Integer.parseInt(tokens[2]);
                timeCost = Integer.parseInt(tokens[3]);   
                
                // get index's
                int originCityIndex = getCityArrayIndex(originCity);
                int destinationCityIndex = getCityArrayIndex(destinationCity);
                
                // set values
                CITIES.get(originCityIndex).setTimeCost(destinationCityIndex, timeCost);
                CITIES.get(originCityIndex).setMonetaryCost(destinationCityIndex, monetaryCost);                 
            }
        }     
    }

    public Integer isCityCreated(String cityToCheckFor){
        Integer returnIntegerValue = 0;
        
        
        City currentCity;
        String currentCityName = null;        
        for (int i = 0; i < CITIES.size(); i++) {
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;
            
            // compare
            if (Objects.equals(currentCityName,cityToCheckFor)){
                returnIntegerValue = 1;
            }
            
        }   
        
        return returnIntegerValue;
        
    }
    


    public void printCityArray(){
        City currentCity;
        String currentCityName = null;
        System.out.println("\nTotal cities " + NUMBEROFCITIES);
        for (int i = 0; i < CITIES.size(); i++) {
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;
            System.out.println("\nConnection Data-----");
            System.out.println("\nCity Name: " + currentCityName);
            System.out.println("\nTime costs: " + currentCity.timeCostArray.length);
            
            for (int j = 0; j < currentCity.timeCostArray.length; j++) {
                System.out.println(currentCity.timeCostArray[j]);           
            }            
            
            System.out.println("\nMonetary costs: " + currentCity.monetaryCostArray.length);
            for (int j = 0; j < currentCity.monetaryCostArray.length; j++) {
                System.out.println(currentCity.monetaryCostArray[j]);           
            }             
        }    

    }
    
    public void printDataArrays(int[][] dataArray){

        
        ArrayList<Integer> timeCostArray = new ArrayList();
        ArrayList<Integer> monetaryCostArray = new ArrayList();
        
        City currentCity;
        String currentCityName = null;        
        for (int i = 0; i < NUMBEROFCITIES; i++) {
            System.out.println("\n");
            for (int j = 0; j < NUMBEROFCITIES; j++) {;
                    System.out.println(dataArray[i][j]);

            }


        }
    }    
    
    // getters
    public int getCityArrayNameByIndex(int cityToCheckFor){
        int returnIntegerValue = -1;
        
        City currentCity;
        String currentCityName = null;        
        for (int i = 0; i < CITIES.size(); i++) {
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;
            
            // compare
            if (Objects.equals(currentCityName,cityToCheckFor)){
                returnIntegerValue = i;
            }
            
        }          
        return returnIntegerValue;
    }
    
    public int getCityArrayIndex(String cityToCheckFor){
        int returnIntegerValue = -1;
        
        City currentCity;
        String currentCityName = null;        
        for (int i = 0; i < CITIES.size(); i++) {
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;
            
            // compare
            if (Objects.equals(currentCityName,cityToCheckFor)){
                returnIntegerValue = i;
            }
            
        }          
        return returnIntegerValue;
    }

    public int [][] getDataArrays(String arrayName){
        int dataArray[][] = new int [NUMBEROFCITIES][NUMBEROFCITIES];
        
        int timeCostArray [] = new int [NUMBEROFCITIES];
        int monetaryCostArray[] = new int [NUMBEROFCITIES];
        
        City currentCity;
        String currentCityName = null;        
        for (int i = 0; i < CITIES.size(); i++) {
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;
            timeCostArray = currentCity.timeCostArray;
            monetaryCostArray = currentCity.monetaryCostArray;
            int temp = 0;
            if (Objects.equals(arrayName,"time")){
                for (int j = 0; j < NUMBEROFCITIES; j++) {
                        temp = timeCostArray[j];
                        dataArray[i][j] = temp;

                }
            }
            else{
                for (int j = 0; j < NUMBEROFCITIES; j++) {
                        dataArray[i][j] = monetaryCostArray[j];

                }                
            }
        }
        return dataArray;
    }
    
    // constructors
    public CityArray(){
        CITIES = new ArrayList();
        NUMBEROFCITIES = 0;        
    }
}
