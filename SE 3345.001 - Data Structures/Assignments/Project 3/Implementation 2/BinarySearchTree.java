import java.util.*;
import java.lang.*;
import java.math.*;

// Project 3
// Alex Lundin
// 10-09-2017
// AML140830  
// SE 3345.001
// Data Structures


public class BinarySearchTree {

   public static void main(String arg[]){
		BinarySearchTree b = new BinarySearchTree();
      
		Scanner input = new Scanner (System.in);
		int choice = 8;
		do{
         System.out.println ("" );
         System.out.println ("" );
         System.out.println ("Welcome to the main driver for Project 3. " );
         System.out.println ("Choose from one of the options below: " );
			System.out.println ("1. Insert" );
			System.out.println ("2. Delete" );
         System.out.println ("3. Find Max" );
         System.out.println ("4. Find Min" );
         System.out.println ("5. Contains" );
         System.out.println ("6. In order Tree Traversal" );
         System.out.println ("7. Height" );
   		System.out.println ("8. Number of Nodes" );
   		System.out.println ("9. Done" );
         			
			
         // data entry loop
         choice = 9; 
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
				option1(b);
			}
			else if (choice == 2){
				option2(b);
			}	
         else if (choice == 3){
				option3(b);
			}
         else if (choice == 4){
				option4(b);
			}         
         else if (choice == 5){
				option5(b);
			}         
         else if (choice == 6){
				option6(b);
			}
         else if (choice == 7){
				option7(b);
			}
         else if (choice == 8){
				option8(b);
			}
         else if (choice == 9){
				option9(b);
			}
         else{
            invalidOption();
         }         
      // sentinel value on loop is 9            
		}while (choice != 9);
	}

   //
   //
   // conditional selection structure
   //
   //      
      
      
   // insert
   public static void option1(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 1");
   
   int clientData = -1;
   clientData = treeArgument.getDataInput();
   
   if (clientData != -1){
      if
         (clientData >= 1 && clientData <= 99){
         treeArgument.insert(clientData);
         System.out.println ("Element inserted:");
         }
      else{
         System.out.println ("Invalid number for element:");
      }
   }

   }
   
   // delete
   public static void option2(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 2");
   
   int clientData = -1;
   clientData = treeArgument.getDataInput();
   
   if (clientData != -1){
      if
         (clientData >= 1 && clientData <= 99){
         treeArgument.lazyDelete(clientData);
         System.out.println ("Element deleted:");
         }
      else{
         System.out.println ("Invalid number for element:");
      }
      
   }
   
   
   }
   
   // find max
   public static void option3(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 3"); 
   treeArgument.findMax();   
   }
   
   // find min
   public static void option4(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 4." );
   treeArgument.findMin();
   }
   
   // contains
   public static void option5(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 5." );
   int clientData = -1;
   clientData = treeArgument.getDataInput();
   
   boolean found = false;
   if (clientData != -1){
      if
         (clientData >= 1 && clientData <= 99){
         found = treeArgument.find(clientData);
         if(found){
         System.out.println ("Element found:");
         }
         else
         System.out.println ("Element not found:");
         }
      else{
         System.out.println ("Invalid number for element:");
      }
      
   }  
   }
   
   // in order traversal
   public static void option6(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 6." );
   treeArgument.printInOrder(root);
   }
   
   // height
   public static void option7(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 7." );
      if
         (root != null){
         int height = treeArgument.treeHeight(root);
         System.out.println ("Height = " + height);
      }
   }   
   // number of nodes
   public static void option8(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 8." );
   treeArgument.printNumberOfNodes();
   } 
   // done
   public static void option9(BinarySearchTree treeArgument){
   System.out.println ("Your choice: 9." );
   System.out.println ("Exiting." );
   }    
   // error handling
   public static void invalidOption(){
   System.out.println ("Invalid option, enter a integer from the menu of choices." );
   }


   // BST attributes
	private static  Node root;
   private static int numberOfNodes;
   private static int numberOfDeletedNodes;
   
   // BST default constructor 
	public BinarySearchTree(){
		this.root = null;
	}
    
	// nested Node Class
	// increases encapsulation of class Node
	// this implementation only allows BinarySearchTree to use the functionality
	class Node{
   
      // Node attributes
		private int key;
		private Node leftChild;
		private Node rightChild;
      private boolean isNodeDeleted;
      
      // Node value constructor
		public Node(int key){
		this.key = key;
		leftChild = null;
		rightChild = null;
      this.isNodeDeleted = false;
		}
      
      // setters
      public void setDeleted(){
         //System.out.println("\n Marker.");
         this.isNodeDeleted = true;
         numberOfDeletedNodes++;
      }
      public void setUnDeleted(){
         this.isNodeDeleted = false;
         numberOfDeletedNodes--;
        // System.out.println("\n Unmarker.");
      }
	}	
    
   // BST methods
   public int getDataInput (){
   
   	
   
      // data entry loop
      Scanner input = new Scanner (System.in);
      int dataEntered = -1; 
      int userInput = 0;
      int sentinelValue = -1;  
       
      
      while (sentinelValue == -1) {
          System.out.println("Enter element:");
          try {
              userInput = Integer.parseInt(input.next());
              sentinelValue = 1; // will only get to here if input was a Integer
              dataEntered = userInput ; 
          } catch (NumberFormatException ignore) {
              System.out.println("Invalid input, use integer value.");
          }
      }
      
      return dataEntered;
   }   
   
   public int treeHeight(Node node) {
      if (node == null) 
          return 0;
      else
      {
          // compute the depth of each subtree
          int lDepth = treeHeight(node.leftChild);
          int rDepth = treeHeight(node.rightChild);
    
          // use the larger one
          if (lDepth > rDepth){ 
               return(lDepth+1);
          }
          else{
               return(rDepth+1);
          }
      }
   } 
    
	public boolean findMax(){
		Node current = root;
		Node  maximumNode = root;
		
		while(current!=null){
			if
				(current.isNodeDeleted != true){
				maximumNode = current;
			}
			current = current.rightChild;

			
		}
      
      // found maximum
		if	
			(maximumNode != null){
			System.out.println("\n The maximum node is: " + maximumNode.key);
			return true;
		}
      // did not find maximum
		return false;
	}
      
	public boolean findMin(){
		Node current = root;
		Node  minimumNode = root;
		
		while(current!=null){
			if
				(current.isNodeDeleted != true){
				minimumNode = current;
			}      
      
			current = current.leftChild;
			
		}
      
      // found minimum
		if	
			(minimumNode != null){
			System.out.println("\n The minimum node is: " + minimumNode.key);
			return true;
		}
      // did not find minimum
		return false;
	}
      
	public boolean find(int id){
		Node current = root;
		while(current!=null){
			if(current.key==id){
				return true;
			}else if(current.key>id){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
		}
		return false;
	}
   
	public boolean lazyDelete(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
      
      // loop to find Node for deletion
		while(current.key!=id){
			parent = current;
			if(current.key>id){
				isLeftChild = true;
				current = current.leftChild;
			}else{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current ==null){
				return false;
			}
		}
      
      // mark node for deletion
      if(current.key == id){
         numberOfNodes--;
         current.setDeleted();
         return true;
      }
      
		return false;		
	}
	

	public void insert(int id){
		Node newNode = new Node(id);
      
      
      // case 1
      boolean nodeAlreadyExists = false;
      nodeAlreadyExists= find(id);
      if (nodeAlreadyExists == true){
         //check if deleted for unmarking
         if (newNode.isNodeDeleted == false){
            newNode.setUnDeleted();
            System.out.println("\n The node " + newNode.key + " already exists in a deleted state. Undeleting node now for the insert operation.");
         }
         // at this point, node exists and is not deleted, alert client
         else{
         System.out.println("\n The node " + newNode.key + " already exists, not inserting duplicate.");
         return;        
         }
         

      }
      
      
      
      // case 2
		if(root==null){
			root = newNode;
         numberOfNodes++;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.key){				
				current = current.leftChild;
            // case 3
				if(current==null){
					parent.leftChild = newNode;
               numberOfNodes++;
					return;
				}
			}else{
				current = current.rightChild;
            // case 4
				if(current==null){
					parent.rightChild = newNode;
               numberOfNodes++;
					return;
				}
			}
		}
	}
	public void printInOrder(Node root){
		if(root!=null){
			printInOrder(root.leftChild);
			System.out.print(" " + root.key);
         
         // print * for deleted Nodes
         if
            (root.isNodeDeleted == true){
            System.out.print("*");
         }
         
			printInOrder(root.rightChild);
		}
	}
	public void printNumberOfNodes(){

			System.out.print("\nNumber of nodes " + numberOfNodes + ".");
         System.out.print("\nNumber of deleted nodes " + numberOfDeletedNodes + ".");
	}

}

