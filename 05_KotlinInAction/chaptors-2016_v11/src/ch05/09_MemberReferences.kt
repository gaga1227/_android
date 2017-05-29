package ch05

class Dog(val name: String) {
	fun bark() {
		println("$name Woof!")
	}
}

fun salute() = println("Salute!")

fun main(args: Array<String>) {
	val dogs = listOf(Dog("Lab"), Dog("Sausage"))
	// use lambda to call member method
	dogs.forEach { it.bark() }
	// use member reference to call method
	dogs.forEach(Dog::bark)

	// use bound member reference to capture a reference
	// to the member on a specific object instance
	val sausage = Dog("Shepherd")
	run(sausage::bark)

	// reference to a top level function
	run(::salute)
	// An action of calling a method is saved as a value
	val callSalute = ::salute
	run(callSalute)
	callSalute()
	// call a top level function directly
	salute()
}