package ch04

// using data class modifier
// equals() for comparing instances
// hashCode() for using them as keys in hash-based containers such as HashMap
// toString() for generating string representations showing all the fields in declaration order

// Note: properties that are not declared in the primary constructor
// don’t take part in the equality checks and hashcode calculation
data class ClientData(val name: String, val postalCode: Int) {}


fun main(args: Array<String>) {
	// toString()
	val alice = ClientData("Alice", 3000)
	println(alice)

	// equals()
	// use '==' to compares their values by calling equals under the hood
	val client1 = ClientData("Alice", 3000)
	val client2 = ClientData("Alice", 3000)
	val client3 = ClientData("Alice", 3001)
	println(client1 == client2)
	println(client1 == client3)

	// hashcode()
	val set: HashSet<ClientData> = HashSet()
	set.add(ClientData("Alice", 3000))
	println(set.contains(ClientData("Alice", 3000)))
}
