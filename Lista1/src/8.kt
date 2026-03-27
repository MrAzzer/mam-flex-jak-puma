fun countVowels(word: String): Int {
    val samogloski = setOf('a','e','i','o','u','y','A','E','I','O','U','Y')
    return word.count { it in samogloski }
}

fun main(){
    println(countVowels("Mississipi"))
}