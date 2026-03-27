
fun wylicz() {
    val num: Int = 15;
    for (i in 1..num) {
        if (i%5==0 && i%3==0) {
            println("trzypiec");
            continue;
        }
        if (i%3==0){
            println("trzy");
        }
        if (i%5==0){
            println("piec");
        }

        else if (i%5!=0 && i%3!=0){
            println(i);
        }
    }
}

fun main(){
    wylicz()
}