import java.util.Locale

fun isPalindrome(){
    var word="aBba"
    word=word.lowercase()
    val reversed= word.reversed()
    if (word==reversed){
        println("Yes")
    }

}
fun main(){
    isPalindrome()
}