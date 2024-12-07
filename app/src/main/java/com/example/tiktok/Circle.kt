package com.example.tiktok

import kotlin.random.Random

class Circle  (val radius : Double) : Shape("Circle") {
    //class Circle private constructor  means that you can extend objects off our circle class inside the circle class
  // private val pi = 3.141492
    // private is a visibility modifier which means it's only visible in this class and can't be accessed anywhere else

//constructor(diameter: Double): this(diameter / 2)
    //two constructors that both have a single value that is a double so kotlin doesn't know if we want to call this one or the first one
   //constructor where you need to pass the diameter of the circle
    //division sign is /

    companion object {
        fun randomCircle(): Circle {
            val radius = Random.nextDouble(1.0, 10.0)
            //choose kotlin.random.Random
            //random is already an object class as you don't have to do Random() and can just write a dot
        return Circle(radius)
            //if you put it in a class you need a regular instance but if it's inside companion object then we don't need a instance
        }
        //fun that generates a random circle
        //doesn't need a specific instance of this class


    }
    init {
        println("$name created with radius = $radius")
        println("$name area is ${area()}")
        println("$name perimeter is ${perimeter()}")

    }
    override fun area() = radius * radius * ImportantNumbers.PI

override fun  perimeter() = 2 * radius * ImportantNumbers.PI
    //access the pi variable from importantNumbers
    //can't create functions of the object ImportantNumbers as there is only one single instance of the object
}
//companion objects have functions or properties with singleton behaviour and you can still create instances