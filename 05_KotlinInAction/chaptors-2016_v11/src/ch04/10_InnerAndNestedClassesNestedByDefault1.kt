package ch04

class Outer {
	// Nested class (doesn't store a reference to an outer class)
	class StaticInner

	// Inner class (stores a reference to an outer class)
	inner class Inner {
		fun getOuterClass(): Outer = this@Outer
	}

	fun getOuterFromInner(): Outer {
		return Inner().getOuterClass()
	}
}

fun main(args: Array<String>) {
	println(Outer().getOuterFromInner())
}