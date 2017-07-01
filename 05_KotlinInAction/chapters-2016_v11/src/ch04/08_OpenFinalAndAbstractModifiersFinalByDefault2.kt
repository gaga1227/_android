package ch04

abstract class Animated {
	// 'abstract' method cannot have default implementation, must be overridden
	abstract fun animate()

	// can be overridden with 'open' modifier
	open fun stopAnimating() {
		println("stopAnimating");
	}

	// default as 'final', cannot be overridden
	fun animateTwice() {
		println("animateTwice");
	}
}

class concreteAnimated : Animated() {
	override fun animate() {
		println("concreteAnimated Animate!");
	}

	override fun stopAnimating() {
		super.stopAnimating()
		println("concreteAnimated stopAnimating");
	}
}

fun main(args: Array<String>) {
	val animated = concreteAnimated();

	animated.animate(); // calls overridden implementation
	animated.stopAnimating(); // calls super and overridden implementations
	animated.animateTwice(); // calls super implementation, cannot be overridden without 'open' modifier
}