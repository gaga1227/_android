package ch02

fun main(args: Array<String>) {
	val name = if (args.isNotEmpty()) args[0] else "Kotlin"

	// refer to local variables in string literals
	println("Hello, $name!")
}
