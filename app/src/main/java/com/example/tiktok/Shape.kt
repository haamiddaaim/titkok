package com.example.tiktok

import androidx.annotation.Dimension

abstract class Shape (
    //abstract tells kotlin that the only purpose of the class is other classes inherit from them
    var name: String
    //made var and not val because we want to reassign it below
    //protected means that it's only accessible in this class and all subclasses that inherit shape class
) {

    constructor(name: String,vararg dimension: Double) : this (name)
   //way to create custom shapes on the fly by overloading the constructor
    init {
        println("I am the super class!")
    }
abstract fun area(): Double
//use abstract fun when inside a abstract class
abstract fun perimeter(): Double
//only create abstract functions when you don't know the implementation yet but you know each subclass needs that function

     fun changeName (newName : String){
        name = newName
        //private can be for functions and can only be accessed in this class
    }
}
//Shape is superclass which is the class where other classes inherit from
//making it a open class means this class can be inherited from


/**the code is now way cleaner because now we know that every shape has a area and perimeter and we can tell our subclasses they need to implement them otherwise there will be an error.**/