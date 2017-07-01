package ch05

import ch02.Person

fun main(args: Array<String>) {
	val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
	val numbersMap = mapOf(0 to "zero", 1 to "one")
	val ppl = listOf(Person("John", true), Person("Jane", false))

	// filter copies elements that satisfy the given predicate
	// into a new Collection
	println(numbers.filter { it % 2 == 0 })
	// defines a filter using lambda
	val isMarriedPredicate = { p: Person -> p.isMarried }
	println(ppl.filter(isMarriedPredicate).toString())
	// defines a filter using method
	fun isJohn(p: Person) = "Jane".equals(p.name, true)
	println(ppl.filter(::isJohn).toString()) // using member reference

	// map function applies the given function to each element
	// in the collection and collects the results into a new collection
	println(numbers.sortedDescending().map { "-$it-" })

	// with hash maps
	println(numbersMap.mapValues { it.value.toUpperCase() })
	println(numbersMap.mapKeys { it.key + 1 })
	println(numbersMap.filterKeys { it == 0 })
	println(numbersMap.filterValues { it.equals("one", true) })
}

