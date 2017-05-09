package ch04

interface Clickable {
	fun click() // abstract, no modifier required, must be implemented
	fun showOff() = println("I'm clickable!")

	// has function body, as default methods in Java 8
	fun sameNameSignature() = println("I'm clickable! from Clickable")
	fun sameNameDifferentSignature(type: String) = println("I'm clickable! from Clickable $type")
}

interface Focusable {
	// has function body, as default methods in Java 8
	fun setFocus(focused: Boolean) = println("I ${if (focused) "got" else "lost"} focus.")
	fun sameNameSignature() = println("I'm focusable! from Focusable")
	fun sameNameDifferentSignature(type: Int) = println("I'm clickable! from Focusable $type")
}

abstract class AbstractButton : Focusable, Clickable {
	// abstract class only needs to implement same method from multiple interfaces
	override fun sameNameSignature() {
		// invoke super methods
		super<Clickable>.sameNameSignature() // same as 'Clickable.super.sameNameSignature() in Java'
		super<Focusable>.sameNameSignature() // same as 'Focusable.super.sameNameSignature() in Java'
	}
}

// colon ':' as 'implements' in Java
class Button : Focusable, Clickable {
	// use 'override' modifier to implement a method
	override fun click() = println("I was clicked! from Button class implementation")

	// must implement same method from multiple interfaces
	override fun sameNameSignature() {
		println("Super methods are overridden here")
	}
}

// colon ':' as 'extends' in Java
// abstract class must be initialised here
class Button2 : AbstractButton() {
	// only needs to implement body-less method from interface
	override fun click() = println("I was clicked! from AbstractButton subclass implementation")
}

fun main(args: Array<String>) {
	val button1 = Button()
	button1.click() // calls implementation
	button1.setFocus(true) // calls default implementation in interface
	button1.sameNameSignature() // calls implementation in class
	button1.sameNameDifferentSignature("called from button1"); // calls default implementation in interface
	button1.sameNameDifferentSignature(1); // calls default implementation in interface

	println("----------------------------------------------")

	val button2 = Button2()
	button2.click() // calls subclass implementation
	button2.setFocus(false) // calls default implementation in interface
	button2.sameNameSignature() // calls implementation in parent class
	button2.sameNameDifferentSignature("called from button2"); // calls default implementation in interface
	button2.sameNameDifferentSignature(2); // calls default implementation in interface
}

