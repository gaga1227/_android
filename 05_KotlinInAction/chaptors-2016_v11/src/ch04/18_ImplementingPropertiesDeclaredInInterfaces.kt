package ch04

// interface with abstract property
// classes implementing the interface need to provide a way
// to obtain the value of abstract property
interface User {
	val nickname: String
	fun printName(): String
}

// override abstract property directly in primary constructor with 'override'
class PrivateUser(override val nickname: String) : User {
	override fun printName(): String {
		return nickname
	}
}

// override abstract property with property with a getter
// implemented through a custom getter
class SubscribingUser(val email: String) : User {
	override val nickname: String
		get() = email.substringBefore('@')

	override fun printName(): String {
		return nickname
	}
}

// override abstract property with property with an initializer
class FacebookUser(val id: Int) : User {
	override val nickname: String = getUserName(id)

	private fun getUserName(id: Int): String {
		return "some name $id"
	}

	override fun printName(): String {
		return nickname
	}
}

fun main(args: Array<String>) {
	val privateUser = PrivateUser("gaga")
	val subscribingUser = SubscribingUser("email@some.com")
	val facebookUser = FacebookUser(1)

	println(privateUser.printName())
	println(subscribingUser.printName())
	println(facebookUser.printName())
}