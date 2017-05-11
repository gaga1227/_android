package ch04

// full syntax of using primary constructor
// similar to Java
class User1 constructor(_name: String) {
	val name: String

	// initialisation block
	// executed when the class is created through the primary constructor
	init {
		name = _name
	}
}

// initialization code combined with the declaration of the property
class User2 constructor(_name: String) {
	val name = _name
}

// omit the constructor keyword if there are no annotations or
// visibility modifiers on the primary constructor
class User3(_name: String) {
	val name = _name
}

// most concise syntax
class User4(val name: String)

// with default values
class User5(val name: String = "John")

fun main(args: Array<String>) {
	val john = User5()
	println(john.name)
	val bob = User5("Bob")
	println(bob.name)
}
