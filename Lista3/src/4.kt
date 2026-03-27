fun countElements(xs: List<List<String>>): Map<String, Int> =
    xs.flatten()
        .groupingBy { it }
        .eachCount()

fun main() {
    val input = listOf(
        listOf("a", "b", "c"),
        listOf("c", "d", "f"),
        listOf("d", "f", "g")
    )

    val result = countElements(input)
    println(result)}
