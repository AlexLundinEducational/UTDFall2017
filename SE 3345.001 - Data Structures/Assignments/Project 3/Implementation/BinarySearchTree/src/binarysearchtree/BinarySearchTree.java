
package binarysearchtree;

/**
 *
 * @author Alex
 */


public class BinarySearchTree {
	private static  Node root;
	public BinarySearchTree(){
		this.root = null;
	}
    
        // nested Node Class
        // increases encapsulation of class Node
        // this implementation only allows BinarySearchTree to use the functionality
        class Node{
                private int key;
                private Node leftChild;
                private Node rightChild;	
                public Node(int key){
                        this.key = key;
                        leftChild = null;
                        rightChild = null;
                }
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
	public boolean delete(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
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
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.leftChild==null && current.rightChild==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.leftChild = null;
			}else{
				parent.rightChild = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.rightChild==null){
			if(current==root){
				root = current.leftChild;
			}else if(isLeftChild){
				parent.leftChild = current.leftChild;
			}else{
				parent.rightChild = current.leftChild;
			}
		}
		else if(current.leftChild==null){
			if(current==root){
				root = current.rightChild;
			}else if(isLeftChild){
				parent.leftChild = current.rightChild;
			}else{
				parent.rightChild = current.rightChild;
			}
		}else if(current.leftChild!=null && current.rightChild!=null){
			
			//now we have found the minimum element in the rightChild sub tree
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.leftChild = successor;
			}else{
				parent.rightChild = successor;
			}			
			successor.leftChild = current.leftChild;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.rightChild;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.leftChild;
		}
		//check if successor has the rightChild child, it cannot have leftChild child for sure
		// if it does have the rightChild child, add it to the leftChild of successorParent.
//		successsorParent
		if(successsor!=deleleNode.rightChild){
			successsorParent.leftChild = successsor.rightChild;
			successsor.rightChild = deleleNode.rightChild;
		}
		return successsor;
	}
	public void insert(int id){
		Node newNode = new Node(id);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.key){				
				current = current.leftChild;
				if(current==null){
					parent.leftChild = newNode;
					return;
				}
			}else{
				current = current.rightChild;
				if(current==null){
					parent.rightChild = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root){
		if(root!=null){
			display(root.leftChild);
			System.out.print(" " + root.key);
			display(root.rightChild);
		}
	}
	public static void main(String arg[]){
		BinarySearchTree b = new BinarySearchTree();
		b.insert(3);b.insert(8);
		b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
		b.insert(20);b.insert(25);b.insert(15);b.insert(16);
		System.out.println("Original Tree : ");
		b.display(b.root);		
		System.out.println("");
		System.out.println("Check whether Node with value 4 exists : " + b.find(4));
		System.out.println("Delete Node with no children (2) : " + b.delete(2));		
		b.display(root);
		System.out.println("\n Delete Node with one child (4) : " + b.delete(4));		
		b.display(root);
		System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));		
		b.display(root);
	}
}

