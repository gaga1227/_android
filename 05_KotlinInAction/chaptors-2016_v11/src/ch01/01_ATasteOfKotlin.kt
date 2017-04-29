package ch01

data class Person(
		val name: String,
		val age: Int? = null) //nullable type (Int?); default value for argument

fun main(args: Array<String>) {
	val persons = listOf(
			Person("Alice"),
			Person("Bob", age = 39)) //named argument
	val oldestPerson = persons.maxBy { it.age ?: 0 } //lambda expression; "elvis" operator
	println("The oldest is: $oldestPerson") //string template

	// The oldest is: Person(name=Bob, age=39) //auto generated 'toString'
}

