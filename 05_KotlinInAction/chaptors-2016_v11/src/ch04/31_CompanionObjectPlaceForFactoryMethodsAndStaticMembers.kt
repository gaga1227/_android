package ch04

class HasNamedCompanion() {
	// defines companion object to a class
	// only one can be defined
	// can be named
	companion object NamedCompanion {
		fun echo(message: String) {
			println("HasNamedCompanion echoes: $message")
		}
	}
}

class HasCompanion() {
	// by default named 'Companion' when name is omitted
	companion object {
		fun echo(message: String) {
			println("HasCompanion echoes: $message")
		}
	}
}

// extension functions to a companion object
// can be defined in different modules
fun HasCompanion.Companion.extensionEcho(message: String) {
	println("HasCompanion extension echoes: $message")
}

interface StringFactory<in T> {
	fun output(num: T): String
}

// has companion object implementing an interface
class HasCompanionImplementingInterface() {
	companion object : StringFactory<Int> {
		override fun output(num: Int): String {
			return num.toString()
		}
	}
}

fun <T> isFactoryType(factory: StringFactory<T>) {
	println("Is StringFactory type: ${factory is StringFactory}")
}

fun main(args: Array<String>) {
	// can call companion object methods directly
	HasCompanion.echo("hello");
	HasNamedCompanion.echo("hello");

	// or via companion object name
	HasCompanion.Companion.echo("hello");
	HasNamedCompanion.NamedCompanion.echo("hello");

	// calls companion object's extension member
	// via name or directly
	HasCompanion.extensionEcho("hello");
	HasCompanion.Companion.extensionEcho("hello");

	// calls companion object's implementation of an interface
	println(HasCompanionImplementingInterface.Companion.output(999))
	isFactoryType(HasCompanionImplementingInterface)
}