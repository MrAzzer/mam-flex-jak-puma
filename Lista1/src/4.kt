
fun main(){
    var num=28;
    var suma=0;
    for (i in 1..num-1){
        if (num%i==0){
            suma=suma+i;
        }

    }
    if (num==suma){
        println("doskonala")
    }
    if (num<suma){
        println("obfita")
    }
    if (num>suma){
        println("niedokarmiona")
    }


    print(suma)
}