package ch02

fun main(args: Array<String>) {
	// use more complex expressions in curly braces
	// nest double quotes within double quotes within an expression
	println("Hello, ${if (args.isNotEmpty()) args[0] else "Kotlin"}!")
}
