package ch02

import java.util.*

fun main(args: Array<String>) {
	// Uses TreeMap so that the keys are sorted
	val binaryReps = TreeMap<Char, String>()
	// Iterates over the characters from A to F using a range of characters
	// any class implements the Comparable interface can be a range
	for (char in 'A'..'F') {
		// Converts ASCII code to binary
		val binary: String = Integer.toBinaryString(char.toInt())
		// use shorthand map getter
		// same as 'binaryReps.put(char, binary)'
		binaryReps[char] = binary
	}
	// loop through a map by entry
	// can unpack an element of a collection
	// and assign the map key and value to two variables
	for ((letter, letterBinary) in binaryReps) {
		println("$letter = $letterBinary")
	}

	// loop through list with index and value
	val list = arrayListOf("10", "11", "1001")
	for ((index, element) in list.withIndex()) {
		println("$index: $element")
	}
}