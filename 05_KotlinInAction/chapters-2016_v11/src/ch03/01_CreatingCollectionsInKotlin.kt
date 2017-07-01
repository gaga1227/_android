package ch03

val set = setOf(1, 2, 3)
val list = listOf(4, 5, 6)
val map = mapOf(1 to "one", 2 to "two", 3 to "three")
val strings = listOf("first", "middle", "last")
val numbers = setOf(1, 2, 3)

fun printCollection(item: Any) {
	// javaClass is Kotlin’s equivalent of Java’s getClass()
	println("This item is of type: ${item.javaClass}")
}

fun main(args: Array<String>) {
	printCollection(set);
	printCollection(list);
	printCollection(map);

	println("first string: ${strings.first()}");
	println("max number: ${numbers.max()}");
}
