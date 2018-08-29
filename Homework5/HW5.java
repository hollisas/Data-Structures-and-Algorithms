package Homework5;

/**
 * @author Austin Hollis
 *
 * I certify that the following code and the code related
 * to the homework assignment is of my own work.
 * 
 */
public class HW5 {
	public static void main(String[] args) {
		//Create new tree
		BinarySearchTree tree = new BinarySearchTree();
		//Insertion calls
		tree.insert(18);
		tree.insert(24);
		tree.insert(7);
		//Method calls to print trees in order, by level and the
		//parents of a single child node.
		tree.printTreeInOrder();
		System.out.println();
		tree.printLevelOrder();
		System.out.println();
		System.out.println("There are " + tree.parentsOfOne() + " nodes that are parents of a single child node.");
		//Insertion calls
		tree.insert(9);
		tree.insert(11);
		tree.insert(4);
		tree.insert(5);
		tree.insert(3);
		tree.insert(23);
		tree.insert(27);
		tree.insert(2);
		tree.insert(25);
		tree.insert(0);
		//Method calls to print trees in order, by level and the
		//parents of a single child node.
		tree.printTreeInOrder();
		System.out.println();
		tree.printLevelOrder();
		System.out.println();
		System.out.println("There are " + tree.parentsOfOne() + " nodes that are parents of a single child node.");
		//Delete minimum value 
		tree.deleteMin();
		//Method calls to print trees in order, by level and the
		//parents of a single child node.
		tree.printTreeInOrder();
		System.out.println();
		tree.printLevelOrder();
		System.out.println();
		System.out.println("There are " + tree.parentsOfOne() + " nodes that are parents of a single child node.");
		//Insertion calls
		tree.insert(31);	
		tree.insert(19);
		tree.insert(26);
		tree.insert(10);
		tree.insert(65);
		//Method calls to print trees in order, by level and the
		//parents of a single child node.
		tree.printTreeInOrder();
		System.out.println();
		tree.printLevelOrder();
		System.out.println();
		System.out.println("There are " + tree.parentsOfOne() + " nodes that are parents of a single child node.");
	}
}
