fun safeParseAndClassify(input: String?): String {
    if (input.isNullOrBlank()) return "none"

    val number = input.toIntOrNull() ?: return "none"
    return if (number % 2 == 0) "parzysta" else "nieparzysta"
}


fun main() {
    println(safeParseAndClassify("2"))
    println(safeParseAndClassify("a"))

}