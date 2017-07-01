package ch02

interface Expr3
class Num3(val value: Int) : Expr3
class Sum3(val left: Expr3, val right: Expr3) : Expr3

// use when expression for return value
fun eval(e: Expr3): Int =
		when (e) {
		// check the type of the when argument value
			is Num3 -> e.value
			is Sum3 -> eval(e.left) + eval(e.right)
			// requires else branch to cover other possibilities
			// where other classes might implement 'Expr3'
			else -> throw IllegalArgumentException("Unknown expression")
		}

fun main(args: Array<String>) {
	println(eval(Sum3(Sum3(Num3(1), Num3(2)), Num3(4))))
}