package ch02

interface Expr4
class Num4(val value: Int) : Expr4
class Sum4(val left: Expr4, val right: Expr4) : Expr4

// use block when expression for return value
fun eval(e: Expr4): Int =
		when (e) {
			is Num4 -> {
				println("num: ${e.value}")
				e.value // the last expression in the block is the result
			}
			is Sum4 -> {
				val left = eval(e.left)
				val right = eval(e.right)
				println("sum: $left + $right")
				eval(e.left) + eval(e.right) // the last expression in the block is the result
			}
			// requires else branch to cover other possibilities
			// where other classes might implement 'Expr4'
			else -> throw IllegalArgumentException("Unknown expression")
		}

fun main(args: Array<String>) {
	println(eval(Sum4(Sum4(Num4(1), Num4(2)), Num4(4))))
}