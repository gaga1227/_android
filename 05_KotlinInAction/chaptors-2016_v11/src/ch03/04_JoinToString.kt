package ch03

// The function is generic
fun <T> joinToString(
		collection: Collection<T>, // collections that contain elements of any type
		separator: String,
		prefix: String,
		postfix: String): String {

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
	println(joinToString(list, "; ", "(", ")"))
}
