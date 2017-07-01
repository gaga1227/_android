package ch05

fun main(args: Array<String>) {
	// Collection
	val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

	// Sequence just wraps around Collections' iterator()
	// operations on Sequence are lazy vs operations on Collections are eager
	// lazy evaluation processes elements one by one
	// Eager evaluation runs each operation on the entire collection and generates temp results
	// hence is more efficient on large Collections
	val numberSequence = numbers.asSequence()
			.map { it * it } // intermediate operation
			.filter { it % 2 == 0 } // intermediate operation

	// intermediate operation returns another Sequence
	// Intermediate operations are always lazy so this prints nothing
	// the map and filter operations are postponed here
	println(numberSequence)
	// terminal operation returns a result
	// the map and filter operations are applied only
	// when terminal operation is called
	// The terminal operation causes all the postponed computations to be performed
	println(numberSequence.toList()) // terminal operation
}