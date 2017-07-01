package ch02

// class initialised with private properties
class Rectangle(
		private var _height: Int,
		private var _width: Int) {

	// public properties with custom getters/setters
	var height: Int
		get() = _height
		set(value) {
			_height = value
		}

	var width: Int
		get() = _width
		set(value) {
			_width = value
		}

	// property with custom getter
	// doesn’t need a field to store its value
	// computed every time the property is accessed
	val isSquare: Boolean
		get() = width == height

	val area: Int
		get() = width * height
}

fun printRectangle(rec: Rectangle) {
	println("rec is square: ${rec.isSquare}, width: ${rec.width}, height: ${rec.height}, area: ${rec.area}")
}

fun main(args: Array<String>) {
	val rec1 = Rectangle(3, 3);
	val rec2 = Rectangle(3, 5);
	printRectangle(rec1)
	printRectangle(rec2)
}
