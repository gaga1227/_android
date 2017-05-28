package ch05

fun main(args: Array<String>) {
	// run lambda expression block directly with arguments
	// and assigns to val
	val lambdaResult = { x: Int, y: Int ->
		// lambda body with multiple statements
		println("Computing the sum of $x and $y")
		// last statement is the result
		x + y
	}(2, 3)

	// prints '5'
	println(lambdaResult)
}
