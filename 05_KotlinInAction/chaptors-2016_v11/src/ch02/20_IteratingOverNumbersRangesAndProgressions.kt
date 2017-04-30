package ch02

fun main(args: Array<String>) {
	// same as Java
	var i: Int = 10
	while (i > 0) {
		println("while: $i")
		i--;
	}

	// same as Java
	var j: Int = 10
	do {
		// runs unconditionally for the first time
		println("doWhile: $j")
		j--;
	} while (false)

	// loop with range progression
	for (g in 1..10) {
		println("loop range progression: $g")
	}

	// loop with range progression with step
	for (h in 20 downTo 10 step 2) { // Step must be positive
		println("loop range progression with step: $h")
	}

	// loop with non inclusive range progression
	for (l in 1 until 10) {
		println("loop non inclusive range progression: $l")
	}
}