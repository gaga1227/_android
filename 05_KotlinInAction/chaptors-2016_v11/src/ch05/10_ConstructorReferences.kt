package ch05

fun main(args: Array<String>) {
	// An action of creating an instance of "Dog" is saved as a value
	val createDog = ::Dog
	// use constructor reference
	createDog("Lab").bark()
}