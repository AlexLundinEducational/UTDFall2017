/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;
import java.io.*;
import java.util.*;
/**
 *
 * @author Alex
 */

public class CustomFileReader {

    // The name of the file to open.
    public static String FILENAME = null;
    
    // This will reference one line at a time
    private String line = null;
    
    public ArrayList readFile (){
        
        ArrayList<String> returnArrayList = new ArrayList(); 
        
        try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(FILENAME);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            //System.out.println(line);
            returnArrayList.add(line);
        }   

        // Always close files.
        bufferedReader.close();         
        }
        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                FILENAME + "'");                
        }
        
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + FILENAME + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }    

        return returnArrayList;
    }
}
