package ch05

import ch02.Person

fun main(args: Array<String>) {
	val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
	val numbersMap = mapOf(0 to "zero", 1 to "one")
	val ppl = listOf(Person("John", true), Person("Jane", false))

	// define predicates
	val isMarried = { p: Person -> p.isMarried }
	val isEvenNumber = { num: Int -> num % 2 == 0 }
	val isZero = { entry: Map.Entry<Int, String> -> entry.value.equals("zero", true) }

	// check if all elements satisfy the predicate
	println(ppl.all(isMarried))
	// check if any element satisfies the predicate
	println(numbers.any(isEvenNumber))
	// 'all' and 'any' negates each other on same predicate
	println(numbers.all(isEvenNumber) == !numbers.any(isEvenNumber))
	// find first element satisfies the predicate, or return null
	println(numbersMap.entries.find(isZero))
	println(numbersMap.entries.firstOrNull(isZero))
	// counts elements satisfy the predicate
	println(numbers.count(isEvenNumber))
	// less efficient count
	println(numbers.filter(isEvenNumber).size)
}