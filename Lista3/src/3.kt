fun suma(a: List<Int>): Int =
    a.filter { it > 0 }
        .sum()

fun main() {
    val input = listOf(1, -4, 12, 0, -3, 29, -150)
    println(suma(input))
}
