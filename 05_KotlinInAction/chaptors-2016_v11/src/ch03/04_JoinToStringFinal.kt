package ch03

// The function is generic
fun <T> joinToStringFinal(
		collection: Collection<T>,
		separator: String = ", ",
		prefix: String = "",
		postfix: String = ""): String {

	val resultBuilder: StringBuilder = StringBuilder("")

	// append all elements
	for ((index, element) in collection.withIndex()) {
		// 'prefix' for very first item, otherwise 'separator'
		resultBuilder.append(if (index == 0) prefix else separator)
		resultBuilder.append(element)
	}
	// add 'postfix' at last
	resultBuilder.append(postfix)

	return resultBuilder.toString()
}

fun main(args: Array<String>) {
	val list = listOf(1, 2, 3)

	// omit only trailing arguments
	println("use default values: ${joinToStringFinal(list)}")
	println("use default values: ${joinToStringFinal(list, "-")}")

	// use named arguments, can omit some arguments from the middle,
	// specify only the ones you need
	println("use named params: ${joinToStringFinal(list, postfix = "}", prefix = "{")}")
}
