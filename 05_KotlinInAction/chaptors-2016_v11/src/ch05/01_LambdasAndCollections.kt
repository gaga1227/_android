package ch05

data class Datum(val age: Int) {
	fun getIQ(): Int = age * age
}

// convectional way to collection operation with loop
fun findOldest(data: List<Datum>) {
	var maxAge: Int = 0
	var oldest: Datum? = null

	for (item in data) {
		if (item.age > maxAge) {
			maxAge = item.age
			oldest = item
		}
	}

	println(oldest)
}

// using library functions taking lambdas or member references is much more concise
fun findOldestWithLambda(data: List<Datum>) {
	// pass lambda function that specifies what values should be compared
	println(data.maxBy { it.age }) // getter function being passed

	// pass member reference that specifies what values should be compared
	println(data.maxBy(Datum::age))
}

fun findSmartest(data: List<Datum>) {
	// pass lambda function that specifies what values should be compared
	println(data.maxBy { it.getIQ() })
	println(data.maxBy { getIQFromAge(it.age) })

	// pass member reference that specifies what values should be compared
	println(data.maxBy(Datum::getIQ))
}

private fun getIQFromAge(age: Int): Int {
	return age * age
}

fun main(args: Array<String>) {
	val data = listOf(Datum(1), Datum(3), Datum(2))
	findOldest(data)
	findOldestWithLambda(data)
	findSmartest(data)
}