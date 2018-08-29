//package Homework6;
/**
 * 
 * @author Austin Hollis
 * I certify that this code is of my
 * own work.
 *
 */
public class HW6 {
	//Main testing program
	public static void main(String[] args) {
		//Integer array of size 100 created.
		Integer h[] = new Integer[100];
		//Create a new heap.
		MinHeap<Integer> heap = new MinHeap<Integer>(h,0,100);
		//Insert a bajillion items.
		heap.insert(25);
		heap.insert(92);
		heap.insert(9);
		heap.insert(8);
		heap.insert(12);
		heap.insert(2);
		heap.insert(6);
		heap.insert(17);
		heap.insert(14);
		heap.insert(27);
		heap.insert(32);
		heap.insert(21);
		heap.insert(10);
		heap.insert(23);
		heap.insert(54);
		heap.insert(64);
		heap.insert(81);
		heap.insert(100);
		heap.insert(42);
		heap.insert(63);
		heap.insert(98);
		heap.insert(99);
		heap.insert(108);
		heap.insert(127);
		heap.insert(85);
		heap.insert(91);
		heap.insert(69);
		heap.insert(1000);
		heap.insert(203);
		heap.insert(204);
		heap.insert(405);
		heap.insert(306);
		heap.insert(895);
		heap.insert(202);
		heap.insert(105);
		heap.insert(49);
		heap.insert(23);
		heap.insert(3);
		heap.insert(809);
		heap.insert(1001);
		heap.insert(765);
		heap.insert(756);
		heap.insert(567);
		heap.insert(576);
		heap.insert(347);
		heap.insert(374);
		heap.insert(203);
		heap.insert(404);
		heap.insert(969);
		heap.insert(696);
		heap.insert(2456);
		//Print the heap size.
		//System.out.println(heap.heapsize());
		//Print the BreadthFirstSearch in the MinHeap.
		heap.printBreadthFirst();
		//Enter a new line.
		System.out.println();
		//Print DepthFirstSearch in the MinHeap.
		heap.printDepthFirst();
	}
}
