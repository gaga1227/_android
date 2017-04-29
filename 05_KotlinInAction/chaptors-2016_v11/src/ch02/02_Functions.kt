package ch02

// function with block body
// can NOT omit return type with type inference
fun max(a: Int, b: Int): Int {
	// if is an expression with a result value
	// Therefore there is no ternary operator
	return if (a > b) a else b
}

// function with expression body
// can omit return type with type inference
fun min(a: Int, b: Int) = if (a < b) a else b

fun main(args: Array<String>) {
	println("max: " + max(13, 1))
	println("min: " + min(13, 1))
}