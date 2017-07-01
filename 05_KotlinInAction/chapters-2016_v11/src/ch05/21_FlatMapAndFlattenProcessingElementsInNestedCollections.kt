package ch05

class Book(val title: String, val authors: List<String>)

fun main(args: Array<String>) {
	val books = listOf(
			Book("Thursday Next", listOf("Jasper Fforde", "Terry Pratchett")),
			Book("Good Omens", listOf("Terry Pratchett", "Neal Gaiman")))

	println(books.flatMap { it.authors })
	println(books.flatMap(Book::authors).toSet())

	val strings = listOf("abc", "def")
	println(strings.flatMap { it.toList() })
	println(strings.flatMap(String::toList))

	val nestedLists = listOf(listOf(2, 1), listOf(3, 2), listOf(5, 4))
	println(nestedLists.flatten().toSet().sorted())
}
