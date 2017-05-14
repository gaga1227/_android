package ch04

class LengthCounter {
	// You can’t change this property outside of the class
	// changes the visibility of the setter to private
	// uses default setter implementation
	// getter is auto generated
	var counter: Int = 0
		private set

	fun addWord(word: String) {
		counter += word.length
		println("Counter size: $counter")
	}
}

fun main(args: Array<String>) {
	val counter = LengthCounter()
	counter.addWord("1")
	counter.addWord("something")
}