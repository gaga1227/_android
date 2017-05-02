package ch03.Java;

import ch03.View;
import ch03.Button;
import ch03.CollectionFunctions;
import ch03.StringFunctions;
import ch03._08_UtilityFunctionsAsExtensionsKt; // using filename if not specified
import ch03._11_NoOverridingForExtensionFunctionsKt; // using filename if not specified

import java.util.Arrays;
import java.util.List;

public class CallExtensionFunctionFromJava {
	public static void main(String[] args) {
		// from 06_ExtensionFunctions.kt
		char c = StringFunctions.lastChar("Java");
		System.out.println("Java's last char is: '" + c + "'");

		// from 06_JoinToStringFinal.kt
		List<Integer> list = Arrays.asList(1, 2, 3);
		System.out.println("Joined list is: '" + CollectionFunctions.joinToStringFinal(list, "|", "<", ">") + "'");

		// from 08_UtilityFunctionsAsExtensions.kt
		List<String> Strings = Arrays.asList("one", "two", "three");
		System.out.println("Joined string list is: '" + _08_UtilityFunctionsAsExtensionsKt.join(Strings, "~", "{", "}") + "'");

		// from 11_NoOverridingForExtensionFunctions.kt
		View view = new Button();
		view.click(); // will call 'Button.click()'
		_11_NoOverridingForExtensionFunctionsKt.showOff(view); // will call 'View.showOff()'
	}
}
