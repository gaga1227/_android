package ch04

sealed class SealedExpr {
	class Num(val value: Int) : SealedExpr()
	class Sum(val left: SealedExpr, val right: SealedExpr) : SealedExpr()
}

// use when expression for return value
fun eval(e: SealedExpr): Int =
		when (e) {
			// refer to static inner class directly
			is SealedExpr.Num -> e.value
			is SealedExpr.Sum -> eval(e.left) + eval(e.right)
			// requires no else branch to cover other possibilities
			// where all subclasses must be nested in 'SealedExpr'
		}

fun main(args: Array<String>) {
	println(eval(SealedExpr.Sum(SealedExpr.Sum(SealedExpr.Num(1), SealedExpr.Num(2)), SealedExpr.Num(4))))
}