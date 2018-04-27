import java.util.*;
import java.lang.*;
import java.math.*;

// Project 2
// Alex Lundin
// 09-26-2017
// AML140830  
// SE 3345.001
// Data Structures





public class LinkedList<AnyType> {




   // main driver
	public static void main(String[] args) {
   
      // make LinkedList at beginning of main
      LinkedList<Magazine> magazineLinkedList = new LinkedList<Magazine>();
      
		Scanner input = new Scanner (System.in);
		int choice = 8;
		do{
         System.out.println ("" );
         System.out.println ("" );
         System.out.println ("Welcome to the main driver for Project 2. " );
         System.out.println ("Choose from one of the options below: " );
			System.out.println ("1. Make Empty" );
			System.out.println ("2. Find ID" );
         System.out.println ("3. Insert At Front" );
         System.out.println ("4. Delete From Front" );
         System.out.println ("5. Delete ID" );
         System.out.println ("6. Print All Records" );
         System.out.println ("7. Done" );
         			
			
         // data entry loop
         choice = 8; 
         int userInput = 0;
         int sentinelValue = -1;  
          
         
         while (sentinelValue == -1) {;
             try {
                 userInput = Integer.parseInt(input.next());
                 sentinelValue = 1; // will only get to here if input was a Integer
                 choice = userInput ; 
             } catch (NumberFormatException ignore) {
                 System.out.println("Invalid option, enter a integer from the menu of choices.");
             }
         }          
         
         
         
         
         
			if (choice == 1){
				option1(magazineLinkedList);
			}
			else if (choice == 2){
				option2(magazineLinkedList);
			}	
         else if (choice == 3){
				option3(magazineLinkedList);
			}
         else if (choice == 4){
				option4(magazineLinkedList);
			}         
         else if (choice == 5){
				option5(magazineLinkedList);
			}         
         else if (choice == 6){
				option6(magazineLinkedList);
			}
         else if (choice == 7){
				option7(magazineLinkedList);
			}
         else{
            invalidOption();
         }         
      // sentinel value on loop is 7            
		}while (choice != 7);
	}

   //
   //
   // conditional selection structure
   //
   //

   // make Emtpy
   public static void option1(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 1");
   magazineLinkedList.makeEmpty();
   //LinkedList<Magazine> magazineLinkedList = new LinkedList<Magazine>();
   }
   
   // find ID
   public static void option2(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 2");
   
   // data entry loop
   Scanner input = new Scanner (System.in);
   int magazineID = 0; 
   int userInput = 0;
   int sentinelValue = -1;  
    
   
   while (sentinelValue == -1) {
       System.out.println("Enter Magazine ID:");
       try {
           userInput = Integer.parseInt(input.next());
           sentinelValue = 1; // will only get to here if input was a Integer
           magazineID = userInput ; 
       } catch (NumberFormatException ignore) {
           System.out.println("Invalid input, use integer value for the Magazine ID.");
       }
   }   
   
   magazineLinkedList.findID(magazineID);
   
   
   }
   
   // insert at front
   public static void option3(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 3");
   
      // data entry loop
      Scanner input = new Scanner (System.in);
      int magazineID = 0; 
      int userInput = 0;
      int sentinelValue = -1;  
       
      
      while (sentinelValue == -1) {
          System.out.println("Enter Magazine ID:");
          try {
              userInput = Integer.parseInt(input.next());
              sentinelValue = 1; // will only get to here if input was a Integer
              magazineID = userInput ; 
          } catch (NumberFormatException ignore) {
              System.out.println("Invalid input, use integer value for the Magazine ID.");
          }
      }     
      

            
      System.out.println("Enter Magazine Name:");
      String magazineName = input.next();
      
      System.out.println("Enter Magazine Publisher:");
      String magazinePublisher = input.next();
      
      
      Magazine m1 = new Magazine(magazineID, magazineName, magazinePublisher);
      m1.printID();
      
      magazineLinkedList.insertAtFront(m1);   
   }
   
   // delete from front
   public static void option4(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 4." );
   magazineLinkedList.deleteFromFront();
   }
   
   // delete ID
   public static void option5(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 5." );
   
   // data entry loop
   Scanner input = new Scanner (System.in);
   int magazineID = 0; 
   int userInput = 0;
   int sentinelValue = -1;  
    
   
   while (sentinelValue == -1) {
       System.out.println("Enter Magazine ID:");
       try {
           userInput = Integer.parseInt(input.next());
           sentinelValue = 1; // will only get to here if input was a Integer
           magazineID = userInput ; 
       } catch (NumberFormatException ignore) {
           System.out.println("Invalid input, use integer value for the Magazine ID.");
       }
   }   
   
   magazineLinkedList.deleteID(magazineID);   
   
   
   }
   
   // print all Records
   public static void option6(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 6." );
   magazineLinkedList.printAllRecords();
   }
   
   // done
   public static void option7(LinkedList magazineLinkedList){
   System.out.println ("Your choice: 7." );
   }   
   
   // error handling
   public static void invalidOption(){
   System.out.println ("Invalid option, enter a integer from the menu of choices." );
   }
   
   
   
   
   // Linked List Class 
   
   
	private Magazine<AnyType> first = null;
   
	 /**
    * Clear list by setting head to null
	 */	   
	public void makeEmpty() {
      first = null;
      System.out.println ("Head set to null, list cleared.");
	}
   	
	 /**
    * Find a certain node by traversing the Linked List recursivley
	 * @param magazineID
	 */	
	public void findID(int argMagazineID){
      String callingMethod = "findID";
      Magazine<AnyType> argBeforeNode = null;
		traverseList(argBeforeNode, first, argMagazineID, callingMethod);
	}  
	
	 /**
	 * Insert at the front of the list
	 * @param node
	 */
	public void insertAtFront(Magazine<AnyType> node) {
		node.setNext(first);
		first = node;
      System.out.println ("Inserted at from");
	}
	
	 /**
	 * Delete from the front of the list
	 */
	public void deleteFromFront(){
		if(first.getNext()!=null)
			first = first.getNext();
		else first = null;
      System.out.println ("Deleted from front."); 
	}
   
	 /**
    * Delete a certain node by traversing the Linked List recursivley
	 * @param magazineID
	 */		
	public void deleteID(int argMagazineID){
      String callingMethod = "deleteID";
      Magazine<AnyType> argBeforeNode = null;
		traverseList(argBeforeNode, first, argMagazineID, callingMethod);
	}	
	

	 /**
    * Clear list by setting head to null
	 */	
	public void printAllRecords(){
		printList(first);
	}
	
	 /**
	 * Recursively traverse this list and print the node value
	 */
	private void printList(Magazine<AnyType> node) {
   
      // print node
      if(node != null){
         node.printID();
      }
      // return if node is null
      else{
         System.out.println ("No nodes to print.");
         return;
      }
      // recursive call
		if(node.getNext()!=null){ 
         printList(node.getNext());
      }
	}


	 /**
	 * Recursively traverse this list and use conditions to determine action
    * @param beforeNode
    * @param node
    * @param argMagazineID
    * @param callingMethod
	 */	
	private void traverseList(Magazine<AnyType> beforeNode, Magazine<AnyType> node, int argMagazineID, String callingMethod) {

      int currentNodeID = node.getID();
      String findMethod = "findID";
      String deleteMethod = "deleteID";
                        
      
      if (currentNodeID == argMagazineID){
      
         // find operation
         if (callingMethod == findMethod){
         System.out.println ("Found element in list.");
         node.printID();
         return;
         }
         // delete operation
         else if (callingMethod == deleteMethod){
         
            // delete any node, other than head
            if (beforeNode != null){
            deleteNode(beforeNode, node);
            System.out.println ("Deleted element in list.");
            }
            // delete head
            else{
            deleteFromFront();
            
            }
         
         return;
         }
         
         }
   	if(node.getNext()!=null){
         traverseList(node, node.getNext(),argMagazineID,callingMethod);
      }
      // end of list, node not found
      else{
      System.out.println ("End of Linked List reached."); 
      System.out.println ("Magazine ID: " + argMagazineID + " not found for method " + callingMethod);   
      }
      
	}
   
	 /**
    * Delete node by removing the link between them in the list
    * @param beforeNode
    * @param node    
	 */   
	public void deleteNode(Magazine<AnyType> beforeNode, Magazine<AnyType> node){
      
		if(node.getNext()!=null){
         Magazine<AnyType> afterNode = node.getNext();
			beforeNode.setNext(afterNode);
      }
		else{
         beforeNode.setNext(null);
         node = null;
      }
	}
   

         


}

// top level abstract interface
interface IDedObject {

   public int getID();          // Returns the ID of the object
   public void printID();       // Prints the details of the ID
   
}

// implementation of the abstract interface

class Magazine<AnyType> implements IDedObject{

   // attributes
   
   private int magazineID;
   private String magazineName;
   private String publisherName;
   
	private AnyType value;
	private Magazine<AnyType> next;
	
   // constructors
   Magazine(){
   return;
   }
   
   Magazine(int argMagazineID, String argMagazineName, String argPublisherName){
   this.magazineID = argMagazineID;
   this.magazineName = argMagazineName;
   this.publisherName = argPublisherName;
   }     
   
    
   // methods
   
   //Returns the ID of the object
   public int getID(){
      return this.magazineID;
   }	
	
	//Prints the details of the ID
	public void printID(){
	// fill in printer
	System.out.println ("Magazine ID: " + this.magazineID);
	System.out.println ("Magazine Name: " + this.magazineName);
	System.out.println ("Publisher Name: " + this.publisherName);

	}
   
	public Magazine(AnyType value) {
		this.value = value;
	}

	public void setNext(Magazine<AnyType> next) {
		this.next = next;
	}

	public Magazine<AnyType> getNext() {
		return next;
	}

	public AnyType getValue() {
		return value;
	}
}

  
   



