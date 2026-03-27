fun pow(x: Int, y: Int): Int {
    var result = 1  // Zmieniono: inicjalizacja na 1, typ zwrotny
    for (i in 1..y) {
        result *= x  // Zmieniono: poprawna składnia, zmienna 'result'
    }
    return result
}

fun checkArmstrong(number: Int) {
    val original = number
    val d = number.toString().length
    var temp = number
    var result = 0
    while (temp > 0) {
        val digit = temp % 10
        result += pow(digit, d)
        temp /= 10
    }
    if (original == result) {
        println("narcystyczna")
    } else {
        println("nie narcystyczna")
    }
}

fun main() {
    val number: Int = 151
    checkArmstrong(number)
}
