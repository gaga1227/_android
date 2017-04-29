package ch02

enum class Color {
	RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun main(args: Array<String>) {
	for (color in Color.values()) {
		println(color)
	}
}
