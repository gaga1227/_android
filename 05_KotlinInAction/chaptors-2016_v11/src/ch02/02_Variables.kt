package ch02

// variables can omit type with initializer
val text = "this is initialised as String type"
val intNumber = 42
val doubleNumber = 3.22

// Immutable reference
// corresponds to a final variable in Java
val finalString: String = "final string"

// Mutable reference (underlined in IDE)
var nonFinalString: String = "i am initialised"

fun main(args: Array<String>) {
	nonFinalString = "i am reassigned"

	// variables can NOT omit type WITHOUT initializer
	val answer: Int

	// initialize Immutable reference with different values
	// compiler can ensure that only one of the initialization statements will be executed
	if (true) {
		answer = 2
	} else {
		answer = 1
	}

	println("text: $text\n" +
			"intNumber: $intNumber\n" +
			"doubleNumber: $doubleNumber\n" +
			"finalString: $finalString\n" +
			"nonFinalString: $nonFinalString\n" +
			"answer: $answer")
}


