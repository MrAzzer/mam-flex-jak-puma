fun check(N: Int, list: List<Int>): Int {
    if (list.size <= N) return -1

    for (index in N until list.size) {
        val target = list[index]
        val preamble = list.subList(index - N, index)
        if (!hasTwoSum(preamble, target)) {
            return target
        }
    }
    return -1
}
private fun hasTwoSum(preamble: List<Int>, target: Int): Boolean {
    val seen = mutableSetOf<Int>()
    for (num in preamble) {
        if (target - num in seen) return true
        seen.add(num)
    }
    return false
}


fun main(){
    println(check(2, listOf(1, 2, 3, 4, 5, 6)))
    println(check(5, listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)))
}