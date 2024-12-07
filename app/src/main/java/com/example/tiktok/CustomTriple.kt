package com.example.tiktok

class CustomTriple<a : Any,b : Any,c : Any> (var first: a, var second: b, var third : c){
    //a : Any is how to make it a variable nonnull able
    //Any? would only allow nullable types of Any
fun  printTypes(){
    println("The type of first is ${first::class}")
    println("The type of second is ${second::class}")
    println("The type of third is ${third::class}")
        //ctrl + d is how you can copy any line of code you press on
}
//prints all the values above
}
