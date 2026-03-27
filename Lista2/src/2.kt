val <T> List<T>.tail: List<T>
    get() = if (isEmpty()) emptyList() else drop(1)

val <T> List<T>.head: T
    get() = first()

fun main(){
    val l = listOf(1, 2, 3, 4)
    println("head = ${l.head}")
    println("tail = ${l.tail}")

}