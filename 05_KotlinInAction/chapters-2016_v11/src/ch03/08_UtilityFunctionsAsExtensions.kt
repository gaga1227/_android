package ch03

// Utility extension function on a specific type of collection
fun Collection<String>.join(
		separator: String = ", ",
		prefix: String = "",
		postfix: String = ""
): String = joinToStringFinal(separator, prefix, postfix)

fun main(args: Array<String>) {
	println("Join strings only: ${listOf("one", "two", "eight").join(":")}")
}