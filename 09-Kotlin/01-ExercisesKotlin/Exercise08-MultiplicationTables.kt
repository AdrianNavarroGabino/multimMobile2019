// Adrián Navarro Gabino

/*
 * Multiplication tables
 */

fun main() {
    println("Enter a number")
    val a = readLine()!!.toInt()
    
    for(i in 0..10)
    {
        println("$a x $i = ${a * i}")
    }
}
