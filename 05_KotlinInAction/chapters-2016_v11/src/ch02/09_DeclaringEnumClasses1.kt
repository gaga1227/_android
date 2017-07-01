package ch02

enum class CustomColor(
		val h: Int,
		val s: Int,
		val v: Int) {

	RED(0, 100, 100),
	GREEN(120, 100, 100),
	BLUE(240, 100, 100),
	WHITE(0, 0, 100),
	BLACK(0, 0, 0); // must have semicolon here

	fun isWarmColor() = h !in 80..330
}

fun main(args: Array<String>) {
	for (color in CustomColor.values()) {
		println("$color -> H:${color.h}, S:${color.s}, V:${color.v} is ${if (color.isWarmColor()) "warm" else "cold"}")
	}
}
