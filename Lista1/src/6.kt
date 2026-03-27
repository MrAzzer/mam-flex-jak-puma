fun isPrime(number: Int): Boolean {
    if (number < 2) return false
    for (i in 2..kotlin.math.sqrt(number.toDouble()).toInt()) {
        if (number % i == 0) return false
    }
    return true
}

fun main() {
    println(isPrime(3))
}