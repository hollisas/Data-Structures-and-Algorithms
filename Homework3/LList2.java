/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
    Modified to hold Comparable object types by McCauley,
    so we could impose order on the list
    
    Modified to edit the insert method to 
*/

/**
 * I certify that this code is of my own work
 * Austin Hollis
 */
//package for my assignment on my Eclipse IDE
package Homework3;

/** Linked list implementation */
class LList2<E extends Comparable<? super E>> implements List<E> {

	private Link<E> head; // Pointer to list header
	private Link<E> tail; // Pointer to last element
	protected Link<E> curr; // Access to current element
	int cnt; // Size of list

	/** Constructors */
	LList2(int size) {
		this();
	} // Constructor -- Ignore size

	LList2() {
		// initially all pointers point point to same node
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}

	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}

	/** Insert "it" at current position */
	/*
	 * original insert portion
	 * public void insert(E it) { curr.setNext(new Link<E>(it, curr.next())); if
	 * (tail == curr) tail = curr.next(); // New tail cnt++; }
	 */
	/**
	 * Modified by Austin Hollis per instructions on the homework sheet for
	 * Homework3
	 * 
	 */
	public void insert(E it) {
		//Create a new link
		Link<E> newLink = new Link<E>(it, curr.next());
		//check for first entry into list
		if (head == null) {
			//set newLink next to head.next()
			newLink.setNext(head.next());
			//set curr next to newLink
			curr.setNext(newLink);
			//head set to newLink
			head = newLink;
		} 
		else {
			//create a new prev link and set it to curr
			Link<E> prev = curr;
			//set curr to head
			curr = head;
			//check for curr not equal to tail and compare the elements
			while ((curr != tail) && (it.compareTo(curr.element()) > 0)) {
				//set prev to curr
				prev = curr;
				//set curr to curr.next()
				curr = curr.next();
			}
			//check if curr == head
			if (curr == head) {
				//set newLink next to curr
				newLink.setNext(curr);
				//set head to newLink
				head = newLink;
			} 
			//check if prev == tail
			else if (prev == tail) {
				//set prev next to newLink
				prev.setNext(newLink);
				//set tail to prev.next()
				tail = prev.next();
			} 
			else {
				//set newLink next to curr
				newLink.setNext(curr);
				//set prev next to newLink
				prev.setNext(newLink);
			}
		}
		//increment cnt by 1
		cnt++;
	}

	/** Append "it" to list */
	public void append(E it) {
		tail = tail.setNext(new Link<E>(it, null));
		cnt++;
	}

	/** Remove and return current element */
	public E remove() {
		if (curr.next() == null)
			return null; // Nothing to remove
		E it = curr.next().element(); // Remember value
		if (tail == curr.next())
			tail = curr; // Removed last
		curr.setNext(curr.next().next()); // Remove from list
		cnt--; // Decrement count
		return it; // Return value
	}

	public void remove(E value) {
		//create a boolean to keep up with existing keys
		boolean exists = false;
		//compare keys
		if (curr.next().element().compareTo(value) == 0) {
			//if cnt is greater than 1
			if(cnt > 1) {
				//create a temp link
				Link<E> temp = curr.next();
				//set curr next to temp
				curr.setNext(temp);
				//decrease cnt by 1
				cnt--;
			}
			else {
				//set curr, tail and head to null
				curr = tail = head = new Link<E> (null);
				//cnt to 0
				cnt = 0;
			}
		}
		else {
			//iterate through the list
			for(int i=0; i <= cnt; i++) {
				//set curr to curr.next()
				curr = curr.next();
				//if curr.next() is null
				if(curr.next() == null) {
					//print statement for saying not found
					System.out.println("Could not find " + value + " in the links.");
					//set exists to false
					exists = false;
					//break out of the for loop
					break;
				}
				//if exists is not false
				else if(exists != false) {
					//set curr next to curr.next().next()
					curr.setNext(curr.next());
					//decrease by 1
					cnt--;
					//break out of for loop
					//break;
				}
			}
		}
	}

	/** Set curr at list start */
	public void moveToStart() {
		curr = head;
	}

	/** Set curr at list end */
	public void moveToEnd() {
		curr = tail;
	}

	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head)
			return; // No previous element
		Link<E> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr)
			temp = temp.next();
		curr = temp;
	}

	/** Move curr one step right; no change if now at end */
	public void next() {
		if (curr != tail)
			curr = curr.next();
	}

	/** @return List length */
	public int length() {
		return cnt;
	}

	/** @return The position of the current element */
	public int currPos() {
		Link<E> temp = head;
		int i;
		for (i = 0; curr == temp; i++)
			temp = temp.next();
		return i;
	}

	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <= cnt) : "Position out of range";
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next();
	}

	/** @return Current element value */
	public E getValue() {
		if (curr.next() == null)
			return null;
		return curr.next().element();
	}
	
	/*public boolean contains(E key) {
		Link<E> temp = head;
		//for each node in the linked list
		while (temp != null) {
			//check for elements being equal
			if (key.compareTo(temp.element()) == 0) {
				return true;
			}
			//iterate through to the next link
			temp = temp.next();
		}
		// if it is not found in list, return false
		return false;
	}*/
	
	// Extra stuff not printed in the book.

	/**
	 * Generate a human-readable representation of this list's contents that looks
	 * something like this: < 1 2 3 | 4 5 6 >. The vertical bar represents the
	 * current location of the fence. This method uses toString() on the individual
	 * elements.
	 * 
	 * @return The string representation of this list
	 */
	public String toString() {
		// Save the current position of the list
		int oldPos = currPos();
		int length = length();
		StringBuffer out = new StringBuffer((length() + 1) * 4);

		moveToStart();
		out.append("< ");
		for (int i = 0; i < oldPos; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append("| ");
		for (int i = oldPos; i < length; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append(">");
		moveToPos(oldPos); // Reset the fence to its original position
		return out.toString();
	}
}