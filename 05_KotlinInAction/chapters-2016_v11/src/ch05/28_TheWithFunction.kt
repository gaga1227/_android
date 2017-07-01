package ch05

// The with standard library function allows you to call multiple methods on the same
// object without repeating the reference to the object
// use 'with' library function when you need to return the result of the
// lambda code block on a particular receiver
fun alphabet(): String {
	val stringBuilder = StringBuilder()

	// with library function takes two params
	// an instance and a lambda code block
	// with function converts the instance into a receiver of the lambda
	// The value that with returns is the result of executing the lambda code
	// The result is the last expression in the lambda
	return with(stringBuilder, {
		for (letter in 'A'..'Z') {
			// can access the receiver explicitly with 'this'
			this.append(letter)
		}
		// 'this' is omitted
		append("\nNow I know the alphabet!")
		toString()
	})
}

// can further refactor above example to return an expression
// and leaving lambda outside of parentheses for readability
fun alphabetFromExpression() = with(StringBuilder()) {
	for (letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know the alphabet even more!")
	toString()
}

fun main(args: Array<String>) {
	println(alphabet())
	println(alphabetFromExpression())
}