//package Homework8;
/**
 * 
 * @author Austin Hollis
 * I certify that all the code below is of my own work
 * 
 */
public class HW8 {

	public static void main(String[] args) {
		//Test cases below//
		Graphmu graph = new Graphmu(4);
		graph.setEdge(0, 0, 3);
		graph.setEdge(0, 1, 1);
		graph.setEdge(3, 0, 6);
		graph.setEdge(1, 1, 5);
		graph.setEdge(1, 2, 9);
		graph.setEdge(2, 1, 9);
		graph.setEdge(1, 3, 9);
		graph.setEdge(2, 2, 2);
		graph.setEdge(3, 2, 7);
		graph.setEdge(3, 3, 4);
		graph.printList();
		System.out.println();
		
		System.out.println("Are the following values edges?");
		System.out.println("1,1?\n" + graph.isEdge(1, 1));
		System.out.println("2,1?\n" + graph.isEdge(2, 1));
		System.out.println("3,1?\n" + graph.isEdge(3, 1));
		System.out.println("Deleting 1,1.");
		System.out.println("Deleting 2,1.");
		System.out.println("Deleting 1,3.");
		graph.delEdge(1, 1);
		graph.delEdge(2, 1);
		graph.delEdge(1, 3);
		System.out.println("Is 1,1 still an edge?\n" + graph.isEdge(1, 1));
		System.out.println("Is 2,1 still an edge?\n" + graph.isEdge(2, 1));
		System.out.println("Is 1,3 still an edge?\n" + graph.isEdge(1, 3));
		System.out.println();
		
		System.out.println("Weights of the following edges:");
		System.out.println("1,1: " + graph.weight(1, 1));
		System.out.println("0,3: " + graph.weight(0, 3));
		System.out.println("3,0: " + graph.weight(3, 0));
		System.out.println("3,3: " + graph.weight(3, 3));
		System.out.println("1,2: " + graph.weight(1, 2));
		System.out.println("2,2: " + graph.weight(2, 2));
		System.out.println();
		
		System.out.println("The first neighbor to the following vertices are:");
		System.out.println("0 -> " + graph.first(0));
		System.out.println("1 -> " + graph.first(1));
		System.out.println("2 -> " + graph.first(2));
		System.out.println("3 -> " + graph.first(3));
		System.out.println();
		
		System.out.println("The next neighbor to the following vertices are:");
		System.out.println("0 -> " + graph.next(0, 1));
		System.out.println("1 -> " + graph.next(1,1));
		System.out.println("2 -> " + graph.next(2,2));
		System.out.println("3 -> " + graph.next(3,3));
		System.out.println();
		
		graph.printList();
	}

}
