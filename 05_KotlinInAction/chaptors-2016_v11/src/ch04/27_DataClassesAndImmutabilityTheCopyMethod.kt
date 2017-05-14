package ch04

data class ClientDataToCopy(val name: String, val postalCode: Int)

fun main(args: Array<String>) {
	val bob = ClientDataToCopy("Bob", 3000)
	// copy the instances of your classes, changing the values of some properties
	val bobMoved = bob.copy(postalCode = 3001)

	println(bob.toString())
	println(bobMoved.toString())
}

