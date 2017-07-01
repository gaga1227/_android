package ch05


fun main(args: Array<String>) {
	// you cannot return a lambda directly
	// but can wrap it inside a SAM constructor
	// that returns an instance of the functional interface
	fun createSAMInstanceFromLambda(name: String): Runnable {
		// The name of the SAM constructor is the same as
		// the name of the underlying functional interface.
		// The SAM constructor takes a single lambda argument that will
		// be used as the body of the single abstract method in the functional interface
		// and returns an instance of the class implementing the interface.
		return Runnable { println("Your runnable: '${name}' is here!") }
	}

	// creates a runnable instance and run it
	createSAMInstanceFromLambda("John").run()
}

// Some other example in Android
/*
val listener = OnClickListener { view ->
	val text = when (view.id) {
		R.id.button1 -> "First button"
		R.id.button2 -> "Second button"
		else -> "Unknown button"
	}
	toast(text)
}
button1.setOnClickListener(listener)
button2.setOnClickListener(listener)
*/