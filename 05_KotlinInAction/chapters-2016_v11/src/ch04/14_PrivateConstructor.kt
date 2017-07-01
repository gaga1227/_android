package ch04

// class with primary constructor
// cannot be extended due to private constructor
open class PrivateButton private constructor() {}

// class with no primary constructor
// can be extended with public secondary constructor
open class ParentButton {
	val isNew: Boolean

	private constructor() {
		isNew = false
		println("Private constructor: $isNew")
	}

	constructor(isNew: Boolean = true) {
		this.isNew = isNew
		println("Public constructor: $isNew")
	}
}

// subclasses
class ChildButton : ParentButton() {}

fun main(args: Array<String>) {
	// these will call public constructor only
	val parent = ParentButton()
	val child = ChildButton()
}



