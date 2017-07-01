package ch02

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
	// type check using 'is', like 'instanceof' in Java
	if (e is Num) {
		// normal cast e as Num type
		return (e as Num).value
	}
	if (e is Sum) {
		// normal cast e as Sum type
		return eval((e as Sum).left) + eval((e as Sum).right)
	}
	// requires else branch to cover other possibilities
	// where other classes might implement 'Expr'
	throw IllegalArgumentException("Unknown expression")
}

fun main(args: Array<String>) {
	println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}