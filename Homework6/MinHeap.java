//package Homework6;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
    Original code was for MaxHeap from the book.
    I, Austin Hollis, have modified it to work for a MinHeap,
    using a 3-ary system. I also changed the removeMax to be
    removeMin and so forth. I have added comments to things
    that I have altered in the code below and the methods that
    I have created are of my own work.
*/

import java.lang.Comparable;

/** Max-heap implementation */
public class MinHeap<E extends Comparable<? super E>> {
	private E[] Heap; // Pointer to the heap array
	private int size; // Maximum size of the heap
	private int n; // Number of things in heap

	/** Constructor supporting preloading of heap contents */
	public MinHeap(E[] h, int num, int max) // renamed from MaxHeap to MinHeap.
	{
		Heap = h;
		n = num;
		size = max;
		buildheap();
	}

	/** @return Current size of the heap */
	public int heapsize() {
		return n;
	}

	/** @return True if pos a leaf position, false otherwise */
	public boolean isLeaf(int pos) {
		return (pos >= n / 3) && (pos < n);
	} // Changed the divisor '2' to be '3'.

	/** @return Position for left child of pos */
	public int leftchild(int pos) {
		assert pos < n / 3 : "Position has no left child"; // Changed Divisor to be 3 instead of 2.
		return (3 * pos) + 1; // Changed multiplier to 3 from 2
	}

	/** 
	 * 
	 * @author Austin Hollis 
	 * @param position integer
	 * @return Position for middle child of pos 
	 * 
	**/
	public int middlechild(int pos) {
		assert n >= (3 * pos) + 2;
		return (3 * pos) + 2;
	}

	/** @return Position for right child of pos */
	public int rightchild(int pos) {
		assert pos < (n - 3) / 3 : "Position has no right child"; // Changed the subtracting 
																  //value and the divisor from
																  //2 to 3.
		return (3 * pos) + 3; // Changed the multiplier and addition value from 2 to 3.
	}

	/** @return Position for parent */
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos - 1) / 3; // changed (pos-1)/2 to (pos-1)/3
	}

	/** Insert val into heap */
	public void insert(E val) {
		assert n < size : "Heap is full";
		int curr = n++;
		Heap[curr] = val; // Start at end of heap
		// Now sift up until curr's parent's key > curr's key
		while ((curr != 0) && (Heap[curr].compareTo(Heap[parent(curr)]) < 0)) {// changed > to <
			DSutil.swap(Heap, curr, parent(curr));
			curr = parent(curr);
		}
	}

	/** Heapify contents of Heap */
	public void buildheap() {
		for (int i = n / 3 - 1; i >= 0; i--) {
			siftdown(i);
		}
	}// changed i=n/2-1 to i=n/3-1

	/** Put element in its correct place */
	private void siftdown(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		while (!isLeaf(pos)) {
			boolean switched = false;
			int j = leftchild(pos);
			if ((j < (n - 2)) && (Heap[j].compareTo(Heap[j + 1]) > 0)) { // less value, changed < to >
																		 // and j<(n-1) to j<(n-2)
				j++; // j is now index of child with greater value
				switched = true;
			}
			if ((j < (n - 2)) && (Heap[j].compareTo(Heap[j + 2]) > 0)) {
				if (switched == true) {
					j++;
				} 
				else {
					j = j + 2;
				}
			}
			if (Heap[pos].compareTo(Heap[j]) >= 0) {
				return;
			}
			DSutil.swap(Heap, pos, j);
			pos = j; // Move down
		}
	}

	/** Remove and return maximum value */
	public E removemin() { // Changed name from removemax to removemin
		assert n > 0 : "Removing from empty heap";
		DSutil.swap(Heap, 0, --n); // Swap maximum with last value
		if (n != 0) { // Not on last element
			siftdown(0); // Put new heap root val in correct place
		}
		return Heap[n];
	}

	/** Remove and return element at specified position */
	public E remove(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		if (pos == (n - 1)) {
			n--; // Last element, no work to be done
		}
		else {
			DSutil.swap(Heap, pos, --n); // Swap with last value
			// If we just swapped in a big value, push it up
			while ((pos > 0) && (Heap[pos].compareTo(Heap[parent(pos)]) > 0)) {
				DSutil.swap(Heap, pos, parent(pos));
				pos = parent(pos);
			}
			if (n != 0) {
				siftdown(pos); // If it is little, push down
			}
		}
		return Heap[n];
	}

	/**
	 * @author Austin Hollis
	 * 
	 */
	public void printBreadthFirst() {
		//Boolean to be used to count the "height"
		boolean levelSize = false;
		//Counter for the height
		int heightCounter = 0;
		//Loop to check for the height of the tree
		while (levelSize == false) {
			//Checks to see if the number of items in the heap is greater than the max number 
			if (n > Math.pow(3, heightCounter)) {
				//Increment height counter
				heightCounter++;
				//Continue under the assumption that there is another level
				levelSize = false;
			} 
			else {
				//Nothing is past this level
				levelSize = true;
			}
		}
		//Set the position counter to 1 since we print out the root node
		int positionCounter = 1;
		//Print out the top value
		System.out.println(Heap[0]);
		//Iterate through the levels
		for (int j = 1; j <= heightCounter; j++) {
			//Reset powerCounter to 0 to know how many to print out
			int powerCounter = 0;
			//While the powerCounter is less than (3^j) or (3**j) or (3 to the power of j) and while the heap at position 
			//counter is not null continue through this while loop
			while ((powerCounter < Math.pow(3, j)) && (Heap[positionCounter] != null)) { //positionCounter <= Math.pow(3,j)
				//Print out the heap value at positionCounter and add a space
				System.out.print(Heap[positionCounter] + " ");
				//Increment positionCounter by 1
				positionCounter++;
				//Increment powerCounter by 1
				powerCounter++;
			}
			//New line for new level
			System.out.println();
		}
	}
	
	/**
	 * @author Austin Hollis
	 * Calls the helper function
	 * 
	 */
	public void printDepthFirst() {
		//Calls the helper function of the printDepthSearch.
		printDepthFirstHelper(0);
	}
	
	/**
	 * 
	 * @author Austin Hollis
	 * @param pos
	 * Helper Function for printDepthFirst method
	 * 
	 */
	private void printDepthFirstHelper(int pos) {
		//Checks for an empty Heap
		if (Heap[pos] == null) {
			System.out.println("Nothing here.");
		} 
		else {
			//Print the position of the heap
			System.out.print(Heap[pos] + ", ");
			//Check for left child position being in the heap
			if (leftchild(pos) < n) {
				//Recursive call of this method for left child
				printDepthFirstHelper(leftchild(pos));
			}
			//Check for middle child position being in the heap
			if (middlechild(pos) < n) {
				//Recursive call of this method for middle child
				printDepthFirstHelper(middlechild(pos));
			}
			//Check for right child position being in the heap
			if (rightchild(pos) < n) {
				//Recursive call of this method for right child
				printDepthFirstHelper(rightchild(pos));
			}
		}
	}
}
