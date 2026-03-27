fun addToBoolean(): Map<Int, Boolean> =
    (1..20).associateWith { it % 2 == 0 }

fun main() {
    println(addToBoolean())}
