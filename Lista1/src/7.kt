fun sumEven(n: Int): Int {
    return (2..n step 2).sum()
}

fun main() {
    println(sumEven(2))
}