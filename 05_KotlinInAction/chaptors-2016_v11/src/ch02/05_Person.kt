package ch02

import ch02.Java.JavaPerson

class Person(
		val name: String, // read-only property: field with getter
		var isMarried: Boolean // mutable property: field with getter and setter
)

fun printPerson(person: Person) {
	// access property directly without getters
	println("${person.name} is isMarried: ${person.isMarried}")
}

fun main(args: Array<String>) {
	val person: Person = Person("Kotlin", true)
	printPerson(person)

	// access property directly without setter
	person.isMarried = false
	printPerson(person)

	// use java class
	val javaPerson: JavaPerson = JavaPerson("Java", false)
	println("${javaPerson.name} is isMarried: ${javaPerson.isMarried}")
}
