package ch05

import ch01.Person

fun main(args: Array<String>) {
	val persons = listOf(
			Person("john", 31),
			Person("marry", 26),
			Person("jane", 42),
			Person("hunter", 26))

	// group by with lambda
	println(persons.groupBy { it.age })

	// group by member reference on extension member to String class
	val strings = listOf("a", "ace", "b", "banana", "c")
	println(strings.groupBy(String::first))
}


