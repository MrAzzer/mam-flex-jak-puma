
fun printPascal(){
    val height = 7
    val rows = mutableListOf<List<Int>>()

    for (i in 0 until height) {

        val row = mutableListOf<Int>()
        for (k in height - i downTo 0){
            print(" ")
        }
        for (j in 0..i) {

            var value =
            if (j == 0 || j == i) {

               1
            } else {
                rows[i - 1][j - 1] + rows[i - 1][j]
            }
            row.add(value)
        }

        rows.add(row)
        println(row.joinToString(" "))
    }
}

fun main(){
    printPascal()
}