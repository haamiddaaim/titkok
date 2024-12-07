package com.example.tiktok

class Rectangle(
    val a: Double,
    val b: Double
) : Shape("Rectangle"){
    //secondary constructors
    //you need to know two different sides of the rectangle which are a and b
    constructor(a: Double) : this(a,a)
    //a: Double causes primary constructor this(a,a) to have two times the same value so the second a inserts for val b above
//how to create a square with one value through the constructor

    //instantiates the class that we define
    //creating a rectangle that is a square only requires you to know one side of the square which means one value
//a,a means we just called the primiry constructor with two times the value
    //: this refers to the primary constructor
    //val a is only allowed in the main constructor above

    constructor(a: Int, b : Int):this(a.toDouble(),b.toDouble())
//how to create a rectangle with integers
    //how to make double values out of integers


    init {
        println("$name created with a = $a and b = $b")
        //prints side length a and b of rectangle
    }

    override fun area() = a * b
    //calculates the area of the rectangle

    override fun perimeter() = 2 * a + 2 * b
    //returns the perimeter of the rectangle

    fun isSquare() = a == b
    //checks if the rectangle is a square
}
//ctrl o brings up menu of override members