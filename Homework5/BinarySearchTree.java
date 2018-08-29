package Homework5;

import java.util.*;
// BinaryTree.java stores int values
// Simple implementation due to Nick Parlante, Stanford University
// Includes insert, lookup, printTreeInOrder
// Other routines added by McCauley

public class BinarySearchTree{
  
  /********************************************************
   --Node--
   The binary search tree is built using this nested node class.
   Each node stores one data element, and has left and right
   sub-tree pointer which may be null.
   The node is a "dumb" nested class -- we just use it for
   storage; it does not have any methods.
  */
		
  private static class Node {
    Node left;
    Node right;
    int data;  

    Node(int newData) {
      left = null;
      right = null;
      data = newData;
    }
  }

  /*******************************************************/
   // Instance variable for tree - Root node pointer. Will be null for an empty tree.
   private Node root;


  /*******************************************************
  * BinaryTree constructor: Creates an empty binary tree -- a null root pointer.
  */
  public BinarySearchTree(){
    root = null;
  }
 

  /*******************************************************
   Returns true if the given target is in the binary tree.
   Uses a recursive helper.
  */
  public boolean lookup(int data) {
    return(lookup(root, data));
  }
 

  /**
   Recursive lookup  -- given a node, recur
   down searching for the given data.
  */
  private boolean lookup(Node node, int data) {
    if (node==null) {
      return(false);
    }

    if (data==node.data) {
      return(true);
    }
    else if (data<node.data) {
      return(lookup(node.left, data));
    }
    else {
      return(lookup(node.right, data));
    }
  }

  /***************************************************************************
   *  Non-recursive lookup  -- given a node, recur
   * down searching for the given data. Added for comparison purposes.
   ***************************************************************************/
   public boolean lookup2(int data) {
       Node walker = root;
       while (walker != null) {
           if (data < walker.data) 
        	      walker = walker.left;
           else if (data > walker.data) 
        	      walker = walker.right;
           else return true;
       }
       return false;
   }
  
  /**************************************************
   Inserts the given data into the binary tree.
   Uses a recursive helper. Does not insert duplicates. 
  */
  public void insert(int data) {
    root = insert(root, data);
  }
 
  /**
   Recursive insert -- given a node pointer, recur down and
   insert the given data into the tree. Returns the new
   node pointer (the standard way to communicate
   a changed pointer back to the caller).
  */
  private Node insert(Node node, int data) {
    if (node==null) {
      node = new Node(data);
      // System.out.println("Making new node and returning its address.");
    }
    else {
      if (data < node.data) {
        node.left = insert(node.left, data);
      }
      else if (data > node.data) {
        node.right = insert(node.right, data);
      }
    }
    return(node); // in any case, return the new pointer to the caller
  }
  
  /**************************************************************
   * Iterative insert method. Added for comparison purposes.
   * Added by RM
   */
  
   public void insert2(int data) {
	  // make new node with data
	  Node z = new Node(data);
	  
	  if (root == null) {
		  root = z;
		  return;
	   }
	   Node parent = null; // will point to the node whose new child is being added
	   Node walker = root; // will walk branch looking for position to insert
	   while (walker != null) {
		  parent = walker;
		  if      (data < walker.data) walker = walker.left;
		  else if (data > walker.data) walker = walker.right;
		  else {  // ignore duplicate just to be different
		      return; 
		  }  
		}
	    // add node as a leaf
		if (data < parent.data) parent.left  = z;
		else parent.right = z;
	}

  
  

  /**
   * inorder traversal and printing
   */
  public void printTreeInOrder(){
	  System.out.println("The tree nodes in inorder:");
	  printInOrder(root);
	  System.out.println();
  }
  
  private void printInOrder(Node t){
	  
	  if (t != null){
		  printInOrder(t.left);
		  System.out.print(t.data + "  ");
		  printInOrder(t.right);
	  }
  }
  
  // iterative version of inorder traversal.
  // Added for comparison purposes.
  // Added by RM
  void printTreeInOrder2() {
	  System.out.println("The tree nodes in inorder:");
      if (root == null) {
          return;
      }
      
      //keep the nodes in the path that are waiting to be visited
      // Uses Java (old) Stack class
      Stack<Node> stack = new Stack<Node>();
      Node t = root;
       
      //first node to be visited will be the left one
      while (t != null) {
          stack.push(t);
          t = t.left;
      }
       
      // traverse the tree
      while (!stack.isEmpty()) {
         
          // visit the top node
          t = stack.pop();
          System.out.print(t.data + " ");
          if (t.right != null) {
              t = t.right;
               
              // the next node to be visited is the leftmost
              while (t != null) {
                  stack.push(t);
                  t = t.left;
              }
          }
      }
	  System.out.println();
  }
  
  // expected that tree not empty, but checked anyway
  public void remove(int x){
	  if( root != null)
	     root = remove(root, x);
  }
  
  // recursive method to remove x from tree rooted at t
  private Node remove(Node t, int x){
	  
	  if (t == null)
		  return t;  // x not found, do nothing
	  if (x < t.data)
		  t.left = remove(t.left, x);
	  else if (x > t.data)
		  t.right = remove(t.right, x);
	  // found x
	  else if (t.left != null && t.right != null){ // two children
		  t.data = findMin(t.right).data;
		  t.right = deleteMin(t.right);
	  }
	  else // zero, or one child (which?) 
		  t = (t.left != null) ? t.left: t.right;
	  return t;
  }
  
  /**
   * iterative remove, expects x to be in tree if tree not empty
   * Added for comparison purposes. Added by RM
   */
   public void remove2(int x){
	   
	  // do nothing if tree empty
      if (root == null)
          return; 
      
      // special case: delete the root
      if(root.data == x){ 
		  root.data = findMin(root.right).data;
		  root.right = deleteMin(root.right); 
		  return; //done
      }
      
      // find node with value to remove in non-root node
      Node parent = null, walker = root;
	  while (walker.data != x) {
		  parent = walker;
		  if (x < walker.data) walker = walker.left;
		  else if (x > walker.data) walker = walker.right;
	  }

	  // node being removed has two children
	  if (walker.left != null && walker.right != null){ // two children
			  walker.data = findMin(walker.right).data;
			  walker.right = deleteMin(walker.right);
	   }
	   else // walker has zero or one child, fix parent's left or right 
			if ( parent.left == walker) 
				// fix parent's left link
				parent.left = (walker.left != null) ? walker.left : walker.right;
			else  // fix parent's right link
				parent.right = (walker.left != null) ? walker.left : walker.right;

  }
  
  // return the minimum value in the tree - it's 
  // got to be as far left as possible - so has a null left 
  // Added by RM
  public int findMin(){
	  if (root != null)
	     return findMin(root).data;
	  return -1; //if called on an empty tree, not a good solution
  }
  
  // assumes called non-empty tree
  // returns a reference to the node containing the min value
  // Added by RM
  private Node findMin(Node t){
	  
	  if (t.left == null)
		  return t;
	  else
		  return findMin(t.left);
  }
  
  // Added by RM
  public void deleteMin(){
	  if (root != null)
	     root = deleteMin(root);
  }
  
  // Added by RM
  private Node deleteMin(Node t){
	  if (t.left == null)
		  t = t.right;
	  else 
	  {
		  t.left = deleteMin(t.left);
	  }
	  return t;
  }
  
  // find height of tree
  // height of empty tree (no nodes) is 0
  // Added by RM
  public int height(){
	  return height(root);
  }

  // find height of tree
  // height of empty tree (no nodes) is 0
  // Added by RM
  private int height(Node t){
	  if (t == null)
		  return 0;
	  else return 1 + Math.max(height(t.left), height(t.right));
  }
  
  /**
   * @author Austin Hollis
   * 
   * @return returns a printed list of the 
   * Nodes and their data in the order of a 
   * tree going by their levels.
   */
  public void printLevelOrder() {
	  //Start at i = 1 instead of 0 since 0 points to
	  //the head node. And go to the height of the tree
	  //in order to get each Node on that level
	  for(int i = 1; i <= this.height(); i++) {
		  //Call the helper function 
		  printGivenLevel(root, i);
		  //Go to the next line which is a new level.
		  System.out.println();
	  }
  }
  //Helps the printLevelOrder method print the level in order.
  /**
   * @author Austin Hollis
   * 
   * @param node
   * @param level
   * 
   * @return Information to the parent/public method type of this
   * function in order to print out the tree in a neat and easy to
   * read fashion.
   */
  public static void printGivenLevel(Node node, int level) {
	  //Check for node being null
	  if(node == null) {
		  //print empty null values
		  System.out.printf("- ");
		  return;
	  }
	  //Check if the level is 1
	  if(level == 1) {
		  //Print out the data of the node
		  System.out.printf("%d ",node.data);
	  }
	  //Iterate through the tree until all values have been visited.
	  else if(level > 1) {
		  //call same function on left child
		  printGivenLevel(node.left,level-1);
		  //call this function on right child
		  printGivenLevel(node.right,level-1);
	  }
  }
  /**
   * @author Austin Hollis
   * 
   * @return an integer value
   */
  public int parentsOfOne() {
	  //calls private function of this method
	  return parentsOfOne(this.root);
  }
  /**
   * @author Austin Hollis
   * 
   * @param root
   * @return information to the public version of this method.
   */
  private int parentsOfOne(Node root) {
	  //check for the left child being null and not the right child
	  if(root.left == null && root.right != null) {
		  //call the right child and add one to total value
		  return 1 + parentsOfOne(root.right);
	  }
	  //check for left child not being null and the right child being null
	  else if(root.left != null && root.right == null) {
		  //call the right child and add one to total value
		  return 1 + parentsOfOne(root.left);
	  }
	  //check if both left and right child are not null
	  else if(root.left != null && root.right != null) {
		  //call function on both children
		  return (parentsOfOne(root.left) + parentsOfOne(root.right));
	  }
	  return 0;
		  
  }
  
  public static void main(String [] args){
	  BinarySearchTree b = new BinarySearchTree();
	  b.insert2(12);
	  b.insert2(120);
	  b.insert2(11);
	  b.insert(8);
	  b.printTreeInOrder();
	  b.printTreeInOrder2();
	  b.insert2(5);
	  b.insert2(38);
	  b.insert2(109);
	  b.insert2(34);
	  b.printTreeInOrder();
	  b.printLevelOrder();
	  //b.remove2(12);
	  b.printTreeInOrder();
	  System.out.print(b.parentsOfOne());
//	  b.deleteMin();
//	  b.printTreeInOrder();
//	  b.deleteMin();
//	  b.printTreeInOrder();
//	  b.deleteMin();
//	  b.printTreeInOrder();
//	  b.deleteMin();
//	  b.printTreeInOrder();
//	  System.out.println("Empty tree has height: " + b.height());
//	  b.insert(1);
//	  System.out.println("After inserting 1, tree height is: " + b.height());
//	  b.insert(12);
//	  System.out.println("After inserting 12 tree height is: " + b.height());
//	  b.insert(222);	 
//	  System.out.println("After inserting 222, tree height is: " + b.height());
//	  b.insert(50);
//	  System.out.println("After inserting 50, tree height is: " + b.height());
//	  b.insert(89);
//	  System.out.println("After inserting 89, tree height is: " + b.height());
//	  b.insert(10);
//	  System.out.println("After inserting 10, tree height is: " + b.height());
//	  b.printTreeInOrder();
//	  b.remove(1);
//	  b.printTreeInOrder();
//	  System.out.println("Tree height is: " + b.height());
//	  System.out.println("\n\nHow many nodes can I add to this tree, without increasing height?");;
//	  b.insert(0);
//	  System.out.println("After inserting 0, tree height is: " + b.height());
//	  b.printTreeInOrder();	  
//	  b.insert(11);
//	  System.out.println("After inserting 11, tree height is: " + b.height());
//	  b.printTreeInOrder();	
//	  b.insert(15);
//	  System.out.println("After inserting 15, tree height is: " + b.height());
//	  b.printTreeInOrder();	
//      System.out.println("\n\nIf you knew what the tree looked like, you'd know how many more. But, a tree");
//      System.out.println("of height 4 has at least 4 nodes (if they are in a chain) ");
//      System.out.println("and no more than 15");
  }
}
  