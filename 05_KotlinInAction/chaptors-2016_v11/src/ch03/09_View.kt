package ch03

// parent class
// add 'open' modifier so it can be inherited
open class View {
	// add 'open' modifier so it can be overridden
	open fun click() = println("View clicked")
}

// subclass class
// inherits View
class Button : View() {
	// add 'override' modifier so it overrides super method
	override fun click() = println("Button view clicked")
}

fun main(args: Array<String>) {
	val view: View = View()
	view.click() // will call 'View.click()

	val buttonView: View = Button()
	buttonView.click() // member function is inherited and overridden, will call 'Button.click()'
}