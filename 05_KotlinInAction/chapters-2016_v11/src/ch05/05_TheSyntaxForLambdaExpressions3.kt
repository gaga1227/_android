package ch05

import ch04.Person

fun main(args: Array<String>) {
	val people = listOf(Person("Alice"), Person("Bob"))
	// "it" is an auto generated argument name
	val names = people.joinToString(separator = "; ", transform = { it.name })
	println(names)
}

