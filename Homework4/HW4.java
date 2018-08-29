package Homework4;

public class HW4 {
	
	public static void main(String args[]) throws StringTooLongException {
		
		StringStack<String> stringStack = new StringStack<String>();
		
		stringStack.push("dog");
		
		System.out.println(stringStack.toString());
		
		System.out.println(stringStack.pop());		
		
		stringStack.push("spy");

		System.out.println(stringStack.pop());		
				
		System.out.println(stringStack.toString());
		
		stringStack.push("hello");
		
		System.out.println(stringStack.toString());
				
		System.out.println(stringStack.pop());		
		
	}
}
