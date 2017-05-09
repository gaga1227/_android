package ch04

// parent class with 'open' modifier
// can be subclassed
open class RichButton : Clickable {
	// override interface method
	// can be overridden in subclass
	override fun click() {
		println("RichButton click")
	}

	// can be overridden in subclass with 'open' modifier
	open fun animate() {
		println("RichButton animate")
	}

	// cannot be overridden in subclass with 'final' modifier
	final override fun showOff() {
		println("RichButton is showOff!")
	}

	// cannot be overridden in subclass, default as 'final'
	fun disable() {
		println("RichButton disabled!")
	}
}

// subclass
// cannot be subclassed without 'open' modifier
class SubRichButton : RichButton() {
	// by default open without 'final' modifier
	// can be overridden when class is open
	override fun click() {
		println("SubRichButton click")
	}

	// by default open without 'final' modifier
	// can be overridden when class is open
	override fun animate() {
		println("SubRichButton animate")
	}
}

fun main(args: Array<String>) {
	val richButton = RichButton()
	val subRichButton = SubRichButton()

	println("richButton:")
	println(richButton.click());
	println(richButton.animate());
	println(richButton.showOff());
	println(richButton.disable());

	println("----------------------------------------------")

	println("subRichButton:")
	println(subRichButton.click());
	println(subRichButton.animate());
	println(subRichButton.showOff());
	println(subRichButton.disable());
}