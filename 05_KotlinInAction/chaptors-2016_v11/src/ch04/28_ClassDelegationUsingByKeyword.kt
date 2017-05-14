package ch04

// use 'by' keyword and delegates MutableCollection implementations
// inside CountingSet to HashSet class via innerSet
class CountingSet<T>(
		val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {

	var objectsAdded = 0

	// Does not delegate; provides a different implementation
	override fun add(element: T): Boolean {
		objectsAdded++
		return innerSet.add(element)
	}

	// Does not delegate; provides a different implementation
	override fun addAll(c: Collection<T>): Boolean {
		objectsAdded += c.size
		return innerSet.addAll(c)
	}
}

fun main(args: Array<String>) {
	val cset = CountingSet<Int>()
	cset.addAll(listOf(1, 1, 2))
	println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}

