// Adrián Navarro Gabino

/*
 * Week days (array)
 */

fun main() {
    val weekDays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday")
    
    println("Enter a day number")
    val a = readLine()!!.toInt()
    
    if(a in 1..7)
    {
        println(weekDays[a - 1])
    }
    else
    {
        println("Wrong day")
        
        for((position, day) in weekDays.withIndex())
        {
            println("Day ${position + 1}: $day")
        }
    }
}