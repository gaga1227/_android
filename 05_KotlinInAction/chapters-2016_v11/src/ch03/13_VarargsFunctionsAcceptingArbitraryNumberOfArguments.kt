package ch03

// Define function with vararg
// as varargs in Java: 'public <T> List<T> customListOf(T... elements) {}'
fun <T> customListOf(vararg elements: T): List<T> = if (elements.isNotEmpty()) elements.asList() else emptyList()

fun main(args: Array<String>) {
	// call varargs method with array requires spread operator: '*' to unpack array to elements
	val unpackedList = customListOf("args unpacked into elements: ", *args)
	println(unpackedList)

	// otherwise array is taken as an object
	val packedList = customListOf("args as packed object: ", args)
	println(packedList)
}
