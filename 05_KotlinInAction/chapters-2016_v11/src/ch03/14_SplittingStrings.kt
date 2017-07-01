package ch03

fun main(args: Array<String>) {
	val numbers = "12.3.56-7.89"

	// split with Regex type
	val nums1A = numbers.split("\\.".toRegex()) // regex string with escaping
	val nums2A = numbers.split("""\.|-""".toRegex()) // no need to escape characters inside triple quoted strings

	// split with String type
	val nums1B = numbers.split('.') // also supports character arguments
	val nums2B = numbers.split(".", "-") // Specifies several delimiters

	println("nums1A == nums1B: ${nums1A == nums1B}")
	println("nums2A == nums2B: ${nums2A == nums2B}")
}

