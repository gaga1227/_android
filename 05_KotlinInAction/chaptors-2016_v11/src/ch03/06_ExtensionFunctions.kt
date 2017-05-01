@file:JvmName("StringFunctions") // compiles to StringFunctions class

package ch03

// add extension function to String class
fun String.lastChar(): Char = this[this.length - 1] // using indexing operator

fun main(args: Array<String>) {
	println("String".lastChar())
}


