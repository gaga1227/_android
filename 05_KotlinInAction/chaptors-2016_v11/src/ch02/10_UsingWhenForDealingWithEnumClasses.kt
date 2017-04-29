package ch02

import ch02.Color.BLUE
import ch02.Color.GREEN
import ch02.Color.INDIGO
import ch02.Color.ORANGE
import ch02.Color.RED
import ch02.Color.VIOLET
import ch02.Color.YELLOW

// Returns a when expression directly
fun getVeggieFromColor(color: Color) =
		when (color) {
			RED -> "Pepper"
			ORANGE -> "Carrot"
			YELLOW -> "Squash"
			GREEN -> "Pea"
			BLUE -> "Lettuce"
			VIOLET -> "Eggplant"
			else -> "N/A" // must cater for all branches, as default branch in Java
		}

// Multiple values in the same branch
fun getTemperatureFromColor(color: Color) =
		when (color) {
			RED, ORANGE, YELLOW -> "Warm"
			GREEN, BLUE, VIOLET, INDIGO -> "Cold"
			else -> "Neutral"
		}

// Switch with any object (e.g. a Set of two colors)
fun mixColors(color1: Color, color2: Color) =
		when (setOf(color1, color2)) {
			setOf(RED, YELLOW) -> ORANGE
			setOf(YELLOW, BLUE) -> GREEN
			setOf(BLUE, VIOLET) -> INDIGO
			else -> throw Exception("Dirty color")
		}

// Saves creations of Set objects
fun mixColorsOptimized(c1: Color, c2: Color) =
		when {
		// No argument for when, branch condition is any boolean expression
			(c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
			(c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
			(c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
			else -> throw Exception("Dirty color")
		}

fun main(args: Array<String>) {
	for (color in Color.values()) {
		println("$color: as ${getVeggieFromColor(color)} is a ${getTemperatureFromColor(color)} color")
	}
	println("\nMix Red and Yellow is ${mixColors(RED, YELLOW)}")
	println("\nMix Yellow and Blue is ${mixColorsOptimized(YELLOW, BLUE)}")
	println("\nMix Green and Blue is ${mixColorsOptimized(GREEN, BLUE)}")
}