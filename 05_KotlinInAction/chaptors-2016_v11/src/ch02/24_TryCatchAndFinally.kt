package ch02

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
	fun readNumber(reader: BufferedReader): Int? {
		try {
			val line = reader.readLine();
			return Integer.parseInt(line)
		} catch (e: NumberFormatException) { // same as Java
			return null
		} finally { // same as Java
			reader.close()
		}
	}

	val reader = BufferedReader(StringReader("not a number"))
	println(readNumber(reader))
}