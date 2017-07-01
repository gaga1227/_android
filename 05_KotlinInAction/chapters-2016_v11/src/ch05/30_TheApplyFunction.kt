package ch05

// apply lets you construct and initialize
// any object using a builder-style API

// 'apply' is an extension function
// use 'apply' function when you need to return the
// receiver of the lambda code block
// can use this to mimic builder pattern in Java

// You create a new instance and immediately passed into apply
// as a receiver of the lambda, once the lambda is executed
// apply returns the initialised instance itself
fun alphabetBuilder() = StringBuilder().apply {
	for (letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know how to build the alphabet!")
}

fun main(args: Array<String>) {
	println(alphabetBuilder().toString())
}