package Homework3;
/**
 * 
 * @author Austin Hollis
 * I certify that this code is of my own work
 *
 */
public class HW3 {

	public static void main(String[] args) {
		//create a List of nodes
		LList2<String> nodes = new LList2<String>();
		//For some reason the code will not add the first item
		//that is inserted.
		nodes.insert("");
		//inserts awesome
		nodes.insert("awesome");
		//inserts t
		nodes.insert("t");
		//prints out the toString
		System.out.println(nodes.toString());
		//insert b	
		nodes.insert("b");
		//insert c
		nodes.insert("c");
		//insert x
		nodes.insert("x");
		//insert e
		nodes.insert("e");
		//insert welcome
		nodes.insert("welcome");
		//print out toString
		System.out.println(nodes.toString());
		//issues removing e in the links
		//In general for some reason my remove has issues finding things
		//and I have no idea why
		nodes.remove("e");
		//inserts hello
		nodes.insert("hello");
		//remove plot
		nodes.remove("plot");
		//insert f
		nodes.insert("f");
		//print toString
		System.out.println(nodes.toString());
		//insert w
		nodes.insert("w");
		//print toString		
		System.out.println(nodes.toString());
	}
}