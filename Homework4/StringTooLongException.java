package Homework4;

public class StringTooLongException extends Exception {

	public StringTooLongException(String msg) {
		super("\n"+msg);
	}
}
