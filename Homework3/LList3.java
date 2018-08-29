/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
    
    Modified to hold Comparable object types by McCauley,
    so we could impose order on the list
*/
package Homework3;
/** Linked list implementation */
class LList3<E extends Comparable<? super E>> implements List<E> {

	private Link<E> head;         // Pointer to list header
	private Link<E> tail;         // Pointer to last element
	protected Link<E> curr;       // Access to current element
	int cnt;		      		  // Size of list

	/** Constructors */
	LList3(int size) { this(); }   // Constructor -- Ignore size
	
	LList3() {
      // initially all pointers point point to same node
	  curr = tail = head = new Link<E>(null); // Create header
	  cnt = 0;
	}
	
	/** Remove all elements */
	public void clear() {
	  head.setNext(null);         // Drop access to links
	  curr = tail = head = new Link<E>(null); // Create header
	  cnt = 0;
	}
	
	/** Insert "it" at current position */
	public void insert(E it) {
	  /*curr.setNext(new Link<E>(it, curr.next()));  
	  if (tail == curr) tail = curr.next();  // New tail
	  cnt++;*/
		cnt++;
		Link<E> newLink = new Link<E>(it, null);
		// first see if the list is empty
		if (head == null) {
			System.out.println("add " + it + " to front");
			head = newLink;
			return;
		}
		// there is something in the list
		// now check to see if it belongs in front
		else if (it.compareTo(curr.element()) > 0) {
			System.out.println("add " + it + "before" + head.element());
			newLink.setNext(head);
			head = newLink;
		}
		// otherwise, step down the list. n will stop
		// at the node after the new node, and trailer will
		// stop at the node before the new node
		else {
			Link<E> after = head.next();
			Link<E> before = head;
			while (after != null) {
				if (it.compareTo(after.element()) < 0)
					break;
				before = after;
				after = after.next();
			}
			// insert between before & after
			newLink.setNext(before.next());
			before.setNext(newLink);
			System.out.println("add " + it + "after" + before.element());
		}
		cnt++;
	}
	
	/** Append "it" to list */
	public void append(E it) {
	  tail = tail.setNext(new Link<E>(it, null));
	  cnt++;
	}
	
	/** Remove and return current element */
	public E remove() {
	  if (curr.next() == null) return null; // Nothing to remove
	  E it = curr.next().element();         // Remember value
	  if (tail == curr.next()) tail = curr; // Removed last
	  curr.setNext(curr.next().next());     // Remove from list
	  cnt--;				// Decrement count
	  return it;                            // Return value
	}
	
	/** Set curr at list start */
	public void moveToStart()
	{ curr = head; }
	/** Set curr at list end */
	public void moveToEnd()
	{ curr = tail; }
	
	/** Move curr one step left; no change if now at front */
	public void prev() {
	  if (curr == head) return; // No previous element
	  Link<E> temp = head;
	  // March down list until we find the previous element
	  while (temp.next() != curr) temp = temp.next();
	  curr = temp;
	}
	
	/** Move curr one step right; no change if now at end */
	public void next()
	  { if (curr != tail) curr = curr.next(); }
	
	/** @return List length */
	public int length() { return cnt; }
	
	/** @return The position of the current element */
	public int currPos() {
	  Link<E> temp = head;
	  int i;
	  for (i=0; curr != temp; i++)
	    temp = temp.next();
	  return i;
	}
	
	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
	  assert (pos>=0) && (pos<=cnt) : "Position out of range";
	  curr = head;
	  for(int i=0; i<pos; i++) curr = curr.next();
	}
	
	/** @return Current element value */
	public E getValue() {
	  if(curr.next() == null) return null;
	  return curr.next().element();
	}
	// Extra stuff not printed in the book.
	
	  /**
	   * Generate a human-readable representation of this list's contents
	   * that looks something like this: < 1 2 3 | 4 5 6 >.  The vertical
	   * bar represents the current location of the fence.  This method
	   * uses toString() on the individual elements.
	   * @return The string representation of this list
	   */
	  public String toString()
	  {
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