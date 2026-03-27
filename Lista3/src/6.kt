fun <T> perm(xs: List<T>): List<List<T>> =
    if (xs.isEmpty()) listOf(emptyList())
    else xs.flatMap { x ->
        perm(xs - x).map { listOf(x) + it }
    }

fun main() {
    val input = listOf(1, 2, 3)
    val perms = perm(input)
    println(perms)
}
