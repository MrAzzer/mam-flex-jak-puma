data class Point(var x: Int, var y: Int)

// p1 + p2
operator fun Point.plus(other: Point): Point =
    Point(this.x + other.x, this.y + other.y)

// p1 += 1
operator fun Point.plusAssign(delta: Int) {
    x += delta
    y += delta
}

// p1 - p2
operator fun Point.minus(other: Point): Point =
    Point(this.x - other.x, this.y - other.y)

// p1 * p2
operator fun Point.times(other: Point): Point =
    Point(this.x * other.x, this.y * other.y)

// p1++
operator fun Point.inc(): Point =
    Point(this.x + 1, this.y + 1)

// p1--
operator fun Point.dec(): Point =
    Point(this.x - 1, this.y - 1)

// !p1
operator fun Point.not(): Point =
    Point(-this.x, -this.y)

fun main() {
    var p1 = Point(1, 1)
    val p2 = Point(2, 2)

    println(p1 + p2)  // (3, 3)

    p1 += 1
    println(p1)       // (2, 2)

    println(p1 - p2)  // (0, 0) dla aktualnego p1; z przykładu chodzi o różnice

    println(p1 * p2)  // mnożenie współrzędnych

    println(p1++)     // zwraca starą wartość, ale p1 stanie się (3, 3)
    println(p1)

    println(p1--)     // analogicznie
    println(p1)

    println(!p1)      // (-x, -y)
}
