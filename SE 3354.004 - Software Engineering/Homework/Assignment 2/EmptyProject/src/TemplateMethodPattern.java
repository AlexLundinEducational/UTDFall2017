/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class TemplateMethodPattern {
    
    public abstract class Party {

            private String name;

            //regular Methods
            public void printName(){
                    System.out.println("Name: " + name);
            }

            //Template methods
            protected abstract void printDetails();
    }

    public class Person extends Party {

            private Date dob;
            private String nation;

            protected void printDetails(){
                    System.out.println ("Details: DOB-" + dob.toString() + ", Nation-" + nation);
            }
    }

    public class Company extends Party {

            private Date incorporated;

            protected void printDetails(){
                    System.out.println ("Details: Incorporated-" + incorporated.toString());
            }
    }    
    
}
