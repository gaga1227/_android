package ch02

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
	fun readNumber(reader: BufferedReader) {
		// use try catch as expression
		val result =
				try {
					Integer.parseInt(reader.readLine()) // return value
				} catch (e: NumberFormatException) {
					return // nothing will be printed, ends here
				}

		println("readNumber: $result")
	}

	fun readNumberAndPrintException(reader: BufferedReader) {
		val result =
				try {
					Integer.parseInt(reader.readLine()) // return value
				} catch (e: NumberFormatException) {
					null // continues to print 'null'
				}

		println("readNumberAndPrintException: $result")
	}

	val reader = BufferedReader(StringReader("not a number"))
	val reader2 = BufferedReader(StringReader("222"))
	readNumber(reader)
	readNumber(reader2)
	readNumberAndPrintException(reader)
}