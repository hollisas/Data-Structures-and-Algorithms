package Homework4;
/**
 * “I certify that this code is my own creation."
 * @author Austin
 *
 * @param <Character>
 */
public class StringStack<Character> {

	private static final int defaultSize = 10;// 10 Strings max to begin
	private static final int maxStringSize = 255;
	private int maxSize; // Maximum size of stack
	private int top; // Index for top Object
	private char[] listArray; // Array holding stack
	char asciiVal;
	
	
	/** Constructors */
    StringStack() { this(defaultSize); }

    @SuppressWarnings("unchecked") // Generic array allocation
    StringStack(int size) {
    	maxSize = size;
		top = 0; 
		listArray = new char[size * (maxStringSize + 1)];   // Create listArray
    }

	/** Reinitialize stack */
	public void clear() {
		top = 0;
	}

	/** Push "it" onto stack 
	 * @throws StringTooLongException */
	public void push(String it) throws StringTooLongException {
		assert top != maxSize : "Stack is full";
		//counter variable
		int tempCounter;
		//number of characters that is adding to the stack
		int numOfChars;
		//character value for the string length
		char tempChar;
		//Checks for size of string being greater than 255
		if(it.length() > maxStringSize) {
			throw new StringTooLongException("String is over the max character limit of 255.");
		}
		//set counter to 0
		tempCounter = 0;
		//loops through and adds the character to the array
		for(int i=it.length()-1; i >= 0; i--) {
			//add charAt at position i of string it to array at position top
			listArray[top] = it.charAt(i);
			//increment temp counter and top
			tempCounter++;
			top++;
		}	
		//store the length of the string into the character array
		numOfChars = tempCounter;
		tempChar = (char)numOfChars;
		listArray[top] = tempChar;
		//increment top
		top++;
	}

	/** Remove and top element */
	public String pop() {
		assert top != 0 : "Stack is empty";
		//length of the string
		int lengthOfString = (int)((char)listArray[top-1]);
		//decrease top by 1
		top--;
		//String to return later
		String testString = "";
		//loop through and remove the characters
		for(int i=top-1; i >= lengthOfString-1; i--) {
			//append to the testString
			testString += listArray[i];
			//decrement top
			//top--;
		}
		//decrease top again
		top--;
		//return the string
		return testString;
	}

	/** @return Top element */
	public char topValue() {
		assert top != 0 : "Stack is empty";
		return listArray[top - 1];
	}

	/** @return Stack size */
	public int length() {
		return top;
	}

	// Extra stuff not printed in the book.

	/**
	 * Generate a human-readable representation of this stack's contents that looks
	 * something like this: < 1 2 3 >. This method uses toString() on the individual
	 * elements.
	 * 
	 * @return The string representation of this stack
	 */
	public String toString() {
		StringBuffer out = new StringBuffer((length() + 1) * 4);
		out.append("< ");
		for (int i = top - 1; i >= 0; i--) {
			out.append(listArray[i]);
			out.append(" ");
		}
		out.append(">");
		return out.toString();
	}
}
