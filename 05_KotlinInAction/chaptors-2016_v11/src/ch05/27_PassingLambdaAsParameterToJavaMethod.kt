package ch05

import ch05.Java.ClassWithMethodTakesFunctionalInterfaceParam

fun main(args: Array<String>) {
	// in Java, functional interface, or SAM interface
	// is interface has only one abstract method hence SAM (Single Abstract Method)
	// You can pass a lambda to any Java method that expects a functional interface
	val javaClass = ClassWithMethodTakesFunctionalInterfaceParam()
	javaClass.runWithDelay(1000) { println("one second's up!") }
}