package ch03.Java;

import ch03.StringFunctions;

public class CallExtensionFunctionFromJava {
	public static void main(String[] args) {
		char c = StringFunctions.lastChar("Java");
		System.out.println("Java's last char is: '" + c + "'");
	}
}
