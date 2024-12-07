package com.example.tiktok

import java.io.Serializable

data class Person (
val name: String,
    val age : Int,
    val country :String
) : Serializable
//serializable means tell kotlin we want to be able to pass this class to an object that can be transferred between activities and can be passed to an intent
//serializable is an interface not a class
//if we want to be able to send this class between activities aka pass this class to an intent...we need to mark this class as serializable


    //data class is to tell kotline that the only purpose of this class to hold data
    //it will also automatically extend that class with a generated tostring functions so we can easily print a person's info without needing to create a personString like on second activity

//it only has () constructor because it only holds data



/*fun  Da (){
    val circle1 = Person( name = "Diann Slater", age = 4764, country = "Oman")

    Person(name = "ajd", age = 4, country = "sdosk")

    //example of using kotlin fill class. fill options pop up when inside the brackets of already established class with variables
}*/
