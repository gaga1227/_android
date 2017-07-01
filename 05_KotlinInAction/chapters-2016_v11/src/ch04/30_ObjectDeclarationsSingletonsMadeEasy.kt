package ch04

import java.io.File

// declare singleton object with 'object' keyword
// cannot have any constructors
object CaseInsensitiveFileComparator : Comparator<File> {
	override fun compare(file1: File, file2: File): Int {
		return file1.getPath().compareTo(file2.getPath(), ignoreCase = true)
	}
}

data class Person(val name: String) {
	// declare singleton object with 'object' keyword
	// cannot have any constructors
	object NameComparator : Comparator<Person> {
		override fun compare(p1: Person, p2: Person): Int = p1.name.compareTo(p2.name)
	}
}

fun main(args: Array<String>) {
	val persons = listOf(Person("Bob"), Person("Alice"))
	println(persons.sortedWith(Person.NameComparator))
}