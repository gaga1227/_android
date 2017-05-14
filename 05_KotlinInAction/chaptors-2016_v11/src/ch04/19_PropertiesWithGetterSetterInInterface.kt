package ch04

// interface can contain properties with getters and setters
// as long as they don't require backing fields
interface UserInterface {
	// must be overridden in subclasses
	val email: String
	// Property not have a backing field: the result value is computed on each access
	// can be inherited
	val nickname: String
		get() = email.substringBefore('@')
}

class NewUser(override val email: String) : UserInterface {
	override fun toString(): String {
		return "$nickname, $email"
	}
}

fun main(args: Array<String>) {
	val newUser = NewUser("email@some.com")
	println(newUser.toString())
}