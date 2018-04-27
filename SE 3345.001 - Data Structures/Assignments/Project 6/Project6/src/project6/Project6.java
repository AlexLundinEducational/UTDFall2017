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
public class Project6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // edit locations of files as needed
        String queryFile = "C:\\Users\\Alex\\Desktop\\Fall 2017\\SE 3345.001 - Data Structures\\Assignments\\Project 6\\Project6\\src\\project6\\Query File.txt";
        String dataFile = "C:\\Users\\Alex\\Desktop\\Fall 2017\\SE 3345.001 - Data Structures\\Assignments\\Project 6\\Project6\\src\\project6\\Data File.txt";
        
        
        // step 1 read text files
        CustomFileReader myReader = new CustomFileReader();
        
        myReader.FILENAME = queryFile;
        ArrayList<String> queryFileData = new ArrayList();
        queryFileData = myReader.readFile();
        
        myReader.FILENAME = dataFile;
        ArrayList<String> dataFileData = new ArrayList();
        dataFileData = myReader.readFile();
        
        // step 2 pass data to the QueryDriver which controls the project flow
        
//        QueryDriver myQueryDriver = new QueryDriver();
//        myQueryDriver.queryReadLoop(queryFileData,dataFileData);

        String buffer = null;
        String originCity = null;
        String destinationCity = null;
        String costType = null;
        
        
        
        // loop through all queryData and solve problem
        for (int i = 0; i < queryFileData.size(); i++) {
            buffer = queryFileData.get(i);

            // after first read
            if (i != 0) {
                // split String data by the delimeter |
                String[] tokens = buffer.split("\\|"); 
                originCity = tokens[0];
                destinationCity = tokens[1];
                costType = tokens[2];
  
                // make origin city
                City myCity = new City(destinationCity);
                // mod (1) delete and view other location
                CityArray myCityArray = new CityArray();
                myCityArray.CITIES.add(myCity);
                myCityArray.NUMBEROFCITIES = CityArray.NUMBEROFCITIES + 1;
               
                
                // project control flow for solutions
                
                // step 3 create cities from data File

                // mod (1) uncomment and view other location
                // CityArray myCityArray = new CityArray();
                myCityArray.cityCreator(dataFileData);
                int originCityIndex = myCityArray.getCityArrayIndex(originCity);

                // step 4 find solution based on queryFileData

                ShortestPath t = new ShortestPath();
                int[][] graph = new int [myCityArray.NUMBEROFCITIES][myCityArray.NUMBEROFCITIES];
                int[][] graph2 = new int [myCityArray.NUMBEROFCITIES][myCityArray.NUMBEROFCITIES];

                if (Objects.equals(costType,"time")) {
                    graph = myCityArray.getDataArrays("time");
                    graph2 = myCityArray.getDataArrays("cost");            
                }
                else{
                    graph = myCityArray.getDataArrays("cost");
                    graph2 = myCityArray.getDataArrays("time");
                }
   
                String solutionString[] = t.dijkstra(graph, originCityIndex, graph2);       
                printSolution(solutionString,costType);                    
                
            }
        }



        
    }
    
    static void printSolution(String solutionString[],String costType){
        
        String cityPath = solutionString[0];
        System.out.print("Prints last leg only. ");
        
        // split String data by the delimeter |
        String[] tokens = cityPath.split("\\|"); 
        String cityIndexString = null;
        int cityIndex;
        String cityName = null;
        // loop through all tokens from CityPath
        for (int i = 0; i < tokens.length; i++) {
            cityIndexString = tokens[i];
            cityIndex = Integer.valueOf(cityIndexString);

            City currentCity = CityArray.CITIES.get(cityIndex);
            cityName = currentCity.getCityName();
            
            System.out.print(cityName);
            
            if(i != (tokens.length - 1)){
               System.out.print("->"); 
            }
            else{
                System.out.print(". "); 
            }

        }
        String cost1 = solutionString[1];
        String cost2 = solutionString[2];
        
        if (Objects.equals(costType,"T")) {
           System.out.print(" Time: " + cost1 + " Cost: " + cost2);
        }
        else{
           System.out.print(" Cost: " + cost1 + " Time: " + cost2);     
        }        

        System.out.println();
        
    }      

}
