package ch02

enum class Color {
	RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class SymbolicColor(val symbol: String) {
	RED("Passion"),
	ORANGE("Kind"),
	YELLOW("Warmth"),
	GREEN("Peace"),
	BLUE("Space"),
	INDIGO("Excitement"),
	VIOLET("Mystery")
}

fun main(args: Array<String>) {
	for (color in Color.values()) {
		println(color)
	}
	for (symbolicColor in SymbolicColor.values()) {
		println("${symbolicColor.name} Symbols ${symbolicColor.symbol}")
	}
}
