//package Homework8;
/**
 * 
 * @author Austin Hollis
 * I certify that all the code below is of my own work
 *
 */
public class Graphmu implements Graph {
	private int numEdge; // Number of edges
	public int[] Mark; // The mark array
	private int size; // The size of the matrix (nxn)
	private int[] adjacencyList; // List to print in triangular

	public Graphmu() {
	} // Constructors

	public Graphmu(int n) {
		Init(n);
	}

	public void Init(int n) {
		Mark = new int[n];
		numEdge = 0;
		// added
		// global variable for the size to be the length 
		// of the matrix either row or column
		size = n;
		// creates the adjacency list with the size of (n*(n+1))/2
		adjacencyList = new int[((n * (n + 1)) / 2)];
	}

	public int n() {
		return Mark.length; // # of vertices
	} 

	public int e() {
		return numEdge; // # of edges
	} 

	/** @return v's first neighbor */
	public int first(int v) {
		for (int i = v; i < Mark.length; i++) {
			// check if there is an edge
			if (isEdge(v, i)) {
				// returns the column
				return i;
			}
		}
		// returns n+1
		return Mark.length; // No edge for this vertex
	}

	/** @return v's next neighbor after w */
	public int next(int v, int w) {
		//iterates through the row starting at w+1
		for (int i = w + 1; i < Mark.length; i++)
			// check to see if there is an edge
			if (isEdge(v, i)) {
				// return i if there is an edge
				return i;
			}
		return Mark.length; // No next edge;
	}

	/** Set the weight for an edge */
	public void setEdge(int i, int j, int wt) {
		assert wt != 0 : "Cannot set weight to 0";
		//Check for i being bigger than j
		if (i > j) {
			//if i is bigger than j swap the values
			//i,j maps to the same edge as j,i
			int temp = i;
			i = j;
			j = temp;
		}
		//get the position of the list that is required
		int pos = listPos(i, j);
		//if the pos in the list is 0 place weight
		if (adjacencyList[pos] == 0) {
			//add weight to pos in adjacency list
			adjacencyList[pos] = wt;
			//increase number of edges
			numEdge++;
		}
	}

	/** Delete an edge */
	public void delEdge(int i, int j) { // Delete edge (i, j)
		//if i is greater than j
		if (i > j) {
			//swap i and j
			//(i,j) maps to (j,i) which is the same
			int temp = i;
			i = j;
			j = temp;
		}
		//find the position needed
		int pos = listPos(i, j);
		//check for the position not being empty at that position
		if (i <= j && adjacencyList[pos] != 0) {
			//make the position have a weight of 0
			adjacencyList[pos] = 0;
			//decrease number of edges
			numEdge--;
		}
	}

	/** Determine if an edge is in the graph */
	public boolean isEdge(int i, int j) {
		//if i is greater than j
		if (i > j) {
			//swap i and j
			//(i,j) maps to (j,i) which is the same
			int temp = i;
			i = j;
			j = temp;
		}
		//find the position needed
		int pos = listPos(i, j);
		//return whether or not the pos is not 0
		return adjacencyList[pos] != 0;
	}

	/** @return an edge's weight */
	public int weight(int i, int j) {
		//If i is greater than j
		if (i > j) {
			//swap i and j
			//(i,j) maps to (j,i) which is the same
			int temp = i;
			i = j;
			j = temp;
		}
		//find the position needed
		int pos = listPos(i, j);
		//return the positions weight
		return adjacencyList[pos];
	}

	/** Set/Get the mark value for a vertex */
	public void setMark(int v, int val) {
		Mark[v] = val;
	}

	public int getMark(int v) {
		return Mark[v];
	}
	
	//added//
	//method to find the position that the values
	//need to go into in the adjacency list.
	public int listPos(int r, int c) {
		//get the global variable of size
		int n = size;
		//get the list position using the formula from the book
		int listPos = (r * n) - (((r * r) + r) / 2) + c;
		//return the position
		return listPos;
	}

	// Prints in upper triangular array format
	public void printList() {
		//prints in array format
		System.out.print("[");
		for (int i = 0; i < adjacencyList.length; i++) {
			System.out.print(adjacencyList[i] + " ");
		}
		System.out.print("]");
		System.out.println();
		//prints in upper triangular array format
		int num = 0;
		for (int r = 0; r < size; r++) {
			for (int c = r; c < size; c++) {
				System.out.print(adjacencyList[num] + " ");
				num++;
			}
			System.out.println();
			for (int x = 0; x <= r; x++) {
				System.out.print("  ");
			}
		}
	}
}
