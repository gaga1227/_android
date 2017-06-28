package ch05

fun main(args: Array<String>) {
	// generate a sequence by defining first element: 0
	// and a lambda to instruct how to generate the next element: it + 2
	// sequences generation is lazy
	// Note: not safe, run terminal operation on infinite sequence causes OOM error
	val evenNumbers: Sequence<Int> = generateSequence(0) { it + 2 }

	// use lambda to instruct when the sequence should stop
	// sequences operation is lazy
	val evenNumbersUnder100 = evenNumbers.takeWhile { it <= 100 }

	// All computation are done only when terminal operation is called
	// converting to Collection is eager
	println(evenNumbersUnder100.toList())

	// can also chain intermediate operations on a sequence
	val oddNumbersUnder100: Sequence<Int> = generateSequence(1) { it + 2 }.takeWhile { it < 100 }
	println(oddNumbersUnder100.toList())
}