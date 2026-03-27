fun srt(xs: List<String>): List<Pair<Char, List<String>>> =
    xs.filter { it.length % 2 == 0 }
        .groupBy { it.first() }
        .mapValues { (_, v) -> v.sorted() }
        .toSortedMap()
        .map { (k, v) -> k to v }

fun main() {
    val input = listOf(
        "cherry",
        "blueberry",
        "citrus",
        "apple",
        "apricot",
        "banana",
        "coconut"
    )

    val result = srt(input)
    println(result)
}
