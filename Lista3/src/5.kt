fun evenPositiveSquare(xs: List<Int>): List<Int> =
    xs.withIndex()
        .filter { (i, v) -> i % 2 == 1 && v > 0 }
        .map { (_, v) -> v * v }

fun main() {
    val input = listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)
    println(evenPositiveSquare(input))
}
