fun <A> isSorted(lst: List<A>, order: (A, A) -> Boolean): Boolean {
    if (lst.size <= 1) return true
    for (i in 0 until lst.lastIndex) {
        if (!order(lst[i], lst[i + 1])) return false
    }
    return true
}

fun main(){
    println(isSorted(listOf(1, 1, 1)) { i: Int, j: Int -> i == j })

}