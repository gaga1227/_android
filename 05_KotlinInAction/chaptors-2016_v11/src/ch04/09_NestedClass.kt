package ch04

import java.io.Serializable

interface State : Serializable

interface View {
	fun getCurrentState(): State
	fun restoreState(state: State)
}

class ButtonView : View {
	override fun getCurrentState() = ButtonState()

	override fun restoreState(state: State) {}

	// same as a static nested class in Java
	// has no reference to outer class
	class ButtonState : State {}
}
