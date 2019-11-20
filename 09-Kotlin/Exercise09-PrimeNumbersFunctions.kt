// Adrián Navarro Gabino

/*
 * Prime numbers (functions)
 */

fun main() {
    println("Enter a number")
    val a = readLine()!!.toInt()
    
    println(if(isPrime(a)) "$a is prime" else "$a is not prime")
}

fun isPrime(n: Int): Boolean {
    if(n == 1)
    {
        return false
    }
    
    for(i in 2..n/2)
    {
        if(n % i == 0)
        {
            return false
        }
    }
    
    return true
}
