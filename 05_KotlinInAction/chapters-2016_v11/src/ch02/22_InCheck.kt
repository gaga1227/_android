package ch02

// use in check on a range
fun isLetter(c: Char): Boolean = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char): Boolean = c !in '0'..'9'

// use in check within when expression
fun recognize(c: Char): String =
		when (c) {
			in '0'..'9' -> "It's a digit!"
			in 'a'..'z', in 'A'..'Z' -> "It's a letter!" // You can combine multiple ranges
			else -> "I don't know..."
		}

fun main(args: Array<String>) {
	println("q is letter: ${isLetter('q')}")
	println("x is not digit: ${isNotDigit('x')}")
	println(recognize('8'))
}