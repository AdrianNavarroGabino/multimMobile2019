// Adrián Navarro Gabino

/*
 * Even or odd
 */

fun main() {
    println("Enter a number")
    var a = readLine()!!.toInt()
    
    println(if (a % 2 == 0) "$a is even" else "$a is odd")
}
