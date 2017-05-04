package ch03

// infix method
// can be used on regular methods and extension functions that have one required parameter
infix fun Double.pow(exp: Double) = Math.pow(this, exp);

infix fun String.means(definition: String) = Pair(this, definition);

fun main(args: Array<String>) {
	val d1: Double = 2.0.pow(3.0) // regular syntax
	val d2: Double = 2.0 pow 3.0 // syntax with infix notation
	println("infix syntax has the same result as regular syntax: ${d1.compareTo(d2) == 0}")

	// destructuring declaration with Pair
	val (dt, dd) = "{term}" means "{definition}"
	println("$dt, by definition, is $dd");

	val dictionary = listOf(
			"Java" means "a class-based, object-oriented language",
			"Kotlin" means "a statically-typed language runs on JVM",
			"Javascript" means "a untyped, interpreted run-time language"
	)
	for ((term, definition) in dictionary) {
		println("$term, by definition, is $definition");
	}
}
