@file:JvmName("CollectionFunctions") // compiles to StringFunctions class

package ch03

// Utility function as extension to 'Collection'
fun <T> Collection<T>.joinToStringFinal(
		separator: String = ", ",
		prefix: String = "",
		postfix: String = ""): String {

	val resultBuilder: StringBuilder = StringBuilder("")

	// 'this' refers to the receiver object: a collection of T
	for ((index, element) in this.withIndex()) {
		resultBuilder.append(if (index == 0) prefix else separator)
		resultBuilder.append(element)
	}
	resultBuilder.append(postfix)

	return resultBuilder.toString()
}

fun main(args: Array<String>) {
	val list = listOf(1, 2, 3)

	println("use default values: ${list.joinToStringFinal()}")
	println("use default values: ${list.joinToStringFinal("-")}")
	println("use named params: ${list.joinToStringFinal(postfix = "}", prefix = "{")}")
}
