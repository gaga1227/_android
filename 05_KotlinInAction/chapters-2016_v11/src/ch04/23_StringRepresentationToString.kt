package ch04

class Client(val name: String, val postalCode: Int) {
	// overrides default string representation of object
	override fun toString(): String {
		return "Client(name=$name, postalCode=$postalCode)"
	}

	// overrides default equals representation of object
	// "Any" is the analogue of java.lang.Object: a superclass of all classes in Kotlin
	// The nullable type "Any?" means "other" can be null.
	override fun equals(other: Any?): Boolean {
		if (other == null || other !is Client) {
			return false
		} else {
			return name == other.name &&
					postalCode == other.postalCode
		}
	}

	// overrides default hashCode representation of object
	override fun hashCode(): Int {
		return name.hashCode() * 31 + postalCode
	}
}

fun main(args: Array<String>) {
	// toString()
	val alice = Client("Alice", 3000)
	println(alice)

	// equals()
	// use '==' to compares their values by calling equals under the hood
	val client1 = Client("Alice", 3000)
	val client2 = Client("Alice", 3000)
	val client3 = Client("Alice", 3001)
	println(client1 == client2)
	println(client1 == client3)

	// hashcode()
	val set: HashSet<Client> = HashSet()
	set.add(Client("Alice", 3000))
	// returns false unless custom hashCode() implementation
	println(set.contains(Client("Alice", 3000)))
}