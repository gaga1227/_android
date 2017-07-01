package ch02

interface Expr2
class Num2(val value: Int) : Expr2
class Sum2(val left: Expr2, val right: Expr2) : Expr2

// use if expression for return value
fun eval(e: Expr2): Int =
		// type check using 'is', like 'instanceof' in Java
		if (e is Num2)
		// use smart-cast after type check with val
			e.value
		else if (e is Sum2)
		// use smart-cast after type check with val
			eval(e.left) + eval(e.right)
		// requires else branch to cover other possibilities
		// where other classes might implement 'Expr2'
		else
			throw IllegalArgumentException("Unknown expression")

fun main(args: Array<String>) {
	println(eval(Sum2(Sum2(Num2(1), Num2(2)), Num2(4))))
}