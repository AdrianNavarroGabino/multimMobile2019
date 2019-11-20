// Adrián Navarro Gabino

/*
 * Week days with when (switch)
 */

fun main() {
    println("Enter a day number")
    var a = readLine()!!.toInt()
    
    when(a)
    {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Saturday")
        5 -> println("Friday")
        6 -> println("Saturday")
        7 -> println("Sunday")
        else -> println("Wrong day")
    }
}