package ch04

class UserWithAddress(val name: String?) {
	// variable with backing field
	var address: String = "unknown"
		set(newAddress) {
			// Updates the backing field value
			// use the special identifier 'field' to access the value of the backing field
			field = newAddress
		}
		get():String {
			// Reads the backing field value
			// use the special identifier 'field' to access the value of the backing field
			return "$field, Germany"
		}

	override fun toString(): String = "${name ?: "John"} lives at $address"
}

fun main(args: Array<String>) {
	val jack = UserWithAddress("Jack")
	jack.address = "new address"
	println(jack.toString())

	val john = UserWithAddress(null)
	println(john.toString())
}


