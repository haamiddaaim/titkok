package com.example.tiktok

import kotlin.math.sqrt

class Triangle(
val a : Double,
val b : Double,
val c : Double): Shape("Triangle") {
    //triangle can have 3 different sides not 2
    //: Shape("Triangle")  is to tell kotlin this is the super class

    //all variables are public by default
    init {
        println("$name created with a = $a, b = $b and c = $c")
        println("$name area is ${area()}")
        println("$name perimeter is ${perimeter()}")
        //since this class inherits from the shape class you can access the name string
    }

   override fun area() = sqrt((perimeter() / 2) * (perimeter() / 2 - a) * (perimeter() / 2 - b) * (perimeter() / 2 - c))
//override fun to tell kotlin that this fun overrides the function of our superclass Shape
   override fun perimeter() = a + b + c


}