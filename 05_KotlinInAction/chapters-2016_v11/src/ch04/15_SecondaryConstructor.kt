package ch04

import java.util.Arrays.asList

// basic class with multiple secondary constructors
// A secondary constructor is introduced using the 'constructor' keyword
// You can declare as many secondary constructors as you need
open class BasicType {
	constructor(name: String) {
		println("BasicType: $name")
	}

	constructor(name: String, numbers: List<Int>) {
		println("BasicType: $name, ${numbers.toString()}")
	}
}

// extend basic type class
// each of secondary constructors calls the corresponding constructor
// of the superclass using the super() keyword
class SomeType : BasicType {
	// Calling superclass constructors
	constructor(name: String) : super(name) {

	}

	// Calling superclass constructors
	constructor(name: String, numbers: List<Int>) : super(name, numbers) {

	}

	// calls another secondary constructor with default value
	// Delegates to another constructor of the class
	constructor() : this("default name") {

	}

	// calls super constructor directly
	constructor(numbers: List<Int>) : super("default name", numbers) {

	}
}

fun main(args: Array<String>) {
	val someType1 = SomeType()
	val someType2 = SomeType(asList(1, 2, 3))
	val someType3 = SomeType("custom name")
	val someType4 = SomeType("custom name", asList(4, 5, 6))
}