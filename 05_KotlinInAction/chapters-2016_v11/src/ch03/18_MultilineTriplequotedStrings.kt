package ch03

// triple-quoted strings can contain any characters, including line breaks
val kotlinLogo = """| //
                   .|//
                   .|/ \"""

fun main(args: Array<String>) {
	println(kotlinLogo.trimMargin("."))
}