package ch05

import ch05.Java.ClassWithMethodTakesFunctionalInterfaceParam

fun main(args: Array<String>) {
	val javaClass = ClassWithMethodTakesFunctionalInterfaceParam()
	var delay: Long

	// in Java, functional interface, or SAM interface
	// is interface has only one abstract method, hence SAM (Single Abstract Method)
	// You can pass a lambda to any Java method that expects a functional interface
	delay = 1000L
	javaClass.runWithDelay(delay) { println("one second's up!") }

	// Using lambda without access to variable from its context like above is equivalent to
	// creating only one instance to a static variable
	// the single instance of the anonymous class that implementing SAM is reused
	delay = 2000L
	val runnable = Runnable { println("two seconds are up!") }
	javaClass.runWithDelay(delay, runnable)

	// Using lambda with access to variable from its context is equivalent to
	// creating different instances of a class for each time
	delay = 3000L
	javaClass.runWithDelay(delay) { println("${delay / 1000} seconds delayed!") }
	delay = 4000L
	javaClass.runWithDelay(delay, SomeRunnable(delay))
}

// Some runnable class kotlin compiles automatically
// for generating instances from lambdas
class SomeRunnable(val delay: Long) : Runnable {
	override fun run() {
		println("${delay / 1000} seconds delayed!")
	}
}