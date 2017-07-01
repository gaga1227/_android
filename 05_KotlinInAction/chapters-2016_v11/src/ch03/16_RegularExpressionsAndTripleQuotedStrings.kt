package ch03

fun parsePath(path: String) {
	val directory = path.substringBeforeLast("\\") // character escaping
	val fullName = path.substringAfterLast("""\""") // no character escaping in triple quoted string

	val fileName = fullName.substringBeforeLast(".")
	val extension = fullName.substringAfterLast(".")

	println("DIR: $directory")
	println("NAME: $fileName")
	println("EXT: $$extension")
}

fun main(args: Array<String>) {
	// triple quoted string requires no character escaping
	parsePath("""C:\Users\johnny\dev\project\main.project.kt""")
}