package ch03

// extension property with getter
val String.lastChar: Char
	get() = get(length - 1)

// extension property with getter/setter
var StringBuilder.lastChar: Char
	get() = get(length - 1)
	set(value: Char) {
		this.setCharAt(length - 1, value)
	}

fun main(args: Array<String>) {
	println("Kotlin?".lastChar)

	val sb = StringBuilder("Kotlin?")
	sb.lastChar = '!'
	println(sb.toString())
}


