package ch03

// Extension functions are not part of the classes;
// they’re declared externally to them
fun View.showOff() = println("View show off")

// Overriding does not apply to extension functions
// Kotlin resolves them statically
fun Button.showOff() = println("Button view show off")

fun main(args: Array<String>) {
	val view: View = Button()
	view.showOff() // will call 'View.showOff()'

	val buttonView: Button = Button()
	buttonView.showOff() // will call 'Button.showOff()
}