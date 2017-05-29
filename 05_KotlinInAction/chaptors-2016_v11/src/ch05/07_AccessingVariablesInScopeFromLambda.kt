package ch05

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
	messages.forEach {
		// Accesses the parameter from the lambda
		// Any time you capture a final variable (val), its value is copied, as in Java
		// use 'it' auto generated argument name
		println("$prefix $it")
	}
}

fun printProblemCounts(responses: Collection<String>) {
	// Java allows you to capture an effectively final variable only
	// but Kotlin can capture mutable variable in lambda
	var clientErrors = 0
	var serverErrors = 0

	// Accesses local variables from the lambda
	// capture a mutable variable (var), its value
	// is stored as an instance of a Ref class.
	// The Ref variable is final and can be easily captured,
	// whereas the actual value is stored in a field
	// and can be changed from the lambda
	responses.forEach {
		response: String ->
		if (response.startsWith("4")) {
			clientErrors++
		} else if (response.startsWith("5")) {
			serverErrors++
		}
	}

	println("$clientErrors client errors, $serverErrors server errors")
}

fun main(args: Array<String>) {
	val errors = listOf("403 Forbidden", "404 Not Found")
	printMessagesWithPrefix(errors, "Error:")

	val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
	printProblemCounts(responses)
}