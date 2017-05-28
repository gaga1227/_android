package ch05

// A lambda expression is always surrounded by curly braces
// The arrow separates the argument list from the body of the lambda
// store a lambda expression in a variable and then treat this variable like a normal function
val sum = { x: Int, y: Int -> x + y }
val negate = { b: Boolean -> !b }
// no arguments
val is24 = { 24 }
// nesting lambda expressions
val pow3 = { x: Int -> x * { x * { x }() }() }

fun main(args: Array<String>) {
	// call a lambda expression directly
	{ println({ "This" + " is " + "closure" }()) }()

	println(sum(1, 2))
	println(negate(1 != 2))
	println(is24())
	println(pow3(3))
}

