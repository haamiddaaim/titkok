package com.example.tiktok

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


fun main() {
    //Abstraction is when you create layers in your code to build seams that you can later pull on and change/replace/refactor things without side-effects

    //intent service is used to do background work so it will continue to work even if our app is minimized
    //if it would run on our UI thread aka main thread then it could freeze the ui
    //you can't use a intent service for multithreading
    //for every service we want to create..we have to create a new class for it to define it's behaviour
    //when creating a service you have to add it to your manifest file
    //main difference between intentservice and service is that intentservice will automatically run in a separate thread so it won't block the main thread
    //service class will support mulithreading but it will run on the main thread by default
    //service will keep running once started until we stop it ourselves but if the android system needs resources...it will kill the service




    //for navigation drawer the constraintlayout won't work because everytime we want to use a navigation drawer we have to use a drawer layout as the root layout
    //navigation view is just the view that slides in our activity when we click on the navigation drawer or swipe from left to right
    //navigation view header is a space on top of our navigation view and we can fill it with other views
    //we need to set the layout gravity of our nv to start because if we don't...our nv would just overlap our whole activity and we want it to be outside of the activity by default
    //fitssystemwindows means that the navigation drawer will leave space for system windows such as the status bar
    //R.string.open, R.string.close are just used for example if blind people are using the app then then strings will be read out loud when they open or close drawer
    //we are not inside of our own app when we show the notification in the notification bar and we have our app closed
    //since the single notification doesn't belong to our app...we can't just execute code as we want because it is shown by another internal app in android called the notification manager



    //viewpager2 is usged to create swipable views and works very similarly to recyclerview...we also need to create a recyclerview adapter for it


    //fragments are a modular section of an activity so they are ui components that you can easily reuse
    //fragments have their own lifecycle and are more lightweight than activities and there's always an activity that hosts one or more fragments and they are affected by activities lifecycle
    //we don't need to create a whole new activity to show a new layout in a fragment...this is much more efficient than creating a new activity for each layout


    //you can create a static fragment by setting the name of the fragment to the fragment you want
    //you can create fragment with a container which can dynamically add replace and remove fragments by changing to Framelayout
    //framelayout is a layout type that is designed to block out an area on the screen to display a single item



    //recyclerviews are able to create list of views and it is called recyclerview because only the visible views will be loaded and the rest will be recycled
    //even though listview has a similar behaviour...you should always prefer recyclerview because it is much more efficient and you can create any kind of list you can imagine
    //because recyclerview doesn't belong to the android standard library...we need to include it in our build.gradle app file
    //you have to create a layout file for a single item in our recyclerview
    //you have to create an adapter for the recyclerview...the adapter job is to create views for the items we have in our recyclerview and the set the contents of our items accordingly
    //each adapter for a recyclerview needs to have an innerclass which is a viewholder class
    //viewholder class is used to hold the views of our recyclerview so the views that we defined in our item_todo xml file



    //spinners are used to let the user choose between many different values...the behaviour is the same as a radio group
    //if you have alot of values like countries or months then a radio group would be a bad choice because every radio button would take up space on your screen
    //a spinner solves that problem since it's just a simple drop down view that shows all available options with a scroller attached
    //if you know the values of the spinner like all the months...you should use a string array that you declare in the strings xml file to reduce boilerplate code
    //boilerplate code is code that isn't necessary which will make your project very messy

    //ctrl + d is copy and paste to the next line
    //ctrl + i to implement the funuctions that are defined in the current function
    //ctrl + . is to condense each function




    //in order to make the toobar appear or disappear...you have to change the parent theme in the themes
    //NoActionBar means the toolbar will not appear and getting rid of it brings it back

    //everytime you want to create a drop menu you have to put in a xml
    //if you want to have an icon for the drop menu list go to drawable and make an image asset and set it to holo dark
    // app:showAsAction="" determines whether the item is shown as an icon or not....
    //never option is used for drop menu
    //always is for icon
    //if room is for if there is room icon if not drop menu
    //withText is similar to never it will just tell android studio you want it as text
    //collapseActionView is advanced and for dynamic views in the menu bar

    //we've only dealt with explicit intents so far and they specify which application will satisfy the intent by providing a specific class name
    //implicit intents don't specify a specific app that will satisfy the intent instead we declare a general action to perform
    //example if we want to take a photo we don't care which photo app the user uses





    //android.permission.ACCESS_COARSE_LOCATION is the location permission in the foreground so if user is in the activity then you can access the location but if the user minimizes your app you cannot
    //ACCESS_BACKGROUND_LOCATION as well to access the location when you're not in the app
    //for android q you have to ask for the foreground and background permission
    //internet permission doesn't need to be manually activated in the app






    //checkbox doesn't need a group like a radio button
    //set android:checked to true if you want something checked by default




    //src stands for source for image
    //scaletype: center will zoom in on the center of the image, centercrop will scale the image the best and crop the left and right side, centerinside will show the show image but not be accurately scaled,
    //fitcenter is the default scaletype and looks the same as Centerinside, fitstart will align the image to the top, fitend will align the image to the bottom, fitxy will stretch the image,
    //matrix is not important and is just for you to use if you want to use a custom matrix to scale the image
    //if you have a image that is for example 16:9 you can write the width as 160dp and height as 90dp which will allow the image to keep it's proportions and you won't need to scale it
    //if you want to make the same image larger you can just double the size with 320dp width and 180dp height
    //in the class you can use .setimageresource to set the image the imageview using a resource from drawable folder



    //shift F6 to rename anything everywhere in the code
    //sp stands for Scale independent pixels and is used for text sizes
    //you can use text style with two styles and separate them like this italic|bold
    //if you want to use android studio default colors instead of create a new one type @android:color/
    //you can use text alignment to center the text etc
    //ellipsize set to "end" will automatically insert 3 dots if your text is too long
    //android:lines will set max amount of lines this text can have
    //ctrl alt l will reformat your code
    //ems is type of graphic unit of font roof that describes how many capital M can fit into the edittext. only has an effect if the width is set to wrap content
    //ctrl space to show all possible options in xml
    //when you have 2 edittexts and want them to be even...constraint them on the opposite sides of each other...then select both chain them with a horizontal chain then change width to 0dp


    //chains will be needed whenever we want to distribute several views next to each other so each one will have the same height and width
    //quick way to center multiple buttons at the same time is to select all of them then use the align button
    //when selecting all buttons right click so you can use chains and make each evenly fill the screen by setting width to 0dp
    //select all and align horizontally and then chain it vertically is how you equally distribute it vertically
    //you can click on pack button and distribute it even more vertically and move the outer buttons more to the outside
    //baseline alignment is when constraintlayout allows you to constrain the baseline of the right button text to baseline of the left button text
    //right click on button to click show baseline
    //guideline is a invisible border you can constrain views to and you can offset the guideline to a specific dp amount or percentage
    //right click on button go to helpers and choose a guideline to use.
    //you can click on the arrow of the guideline to change the direction of the guideline and if you click it again you can see the percentage of screen it has
    //you can constraint your views to the guideline and then it will always be constrained to exactly 50% of the screen
    //you can only constrain the button to a horizontal guideline if the baseline isn't constrained
    //you can remove the baseline by going to attributes - layout - constraints and delete baseline
    //quick way to access hints is to click on the button then search in attributes hint. also delete the text that is already in there so you can show the hint


    //you can build almost every layout you can think of with a constraint layout
    //constraint layout will help achieve a flat layout hierarchy to improve the performance and build layouts easier
    //having other layouts that are not flat will cause bad performance as android will have to calculate more to determine the position of a view on the screen
    //in design tab you can also connect the sides of a button to another side of a button to make it in the center of that button vertically
    //in attributes tab you can remove right constraint by deleting the end constraint and the other button will move as well if connected
    //you can quick remove a constraint by clicking on it and pressing delete
    //if you press the magnet button in design tab and center a button it will automatically get centered horizontally
    //the same thing happens if you drag a button the center with vertical and horizontal being automatically added
    //if you click on 0dp button you will be able to set the margin. margin determines how much space is between views
    //0dp just means that whenever you constraint a view to another one the margin will automatically be set to 16 or you can set it to 0 and do it manually
    //you can use attributes - layout - constraint widget to change the margin in realtime
    //when you move the other button you didn't connect it will always be the margin you set away from the connected button
    //there's one more option for the layout width and height...set the layout width to 0dp and it will fills the whole width of the constraints and only works with constraintlayout
    //clear constraints button is right next to 0dp button
    //quick way to add constraints horizontally and vertically is to use the align button. if there is a button between the button and the parent it will connect to it


    //in order to have the behaviour of putting two edittexts next to each other and ones below that you have to wrap it in 2 linearlayouts with the first being vertical and second being horizontal
    //give both edittexts layout weight so they can equally be the same size horizontally.
    //dp is short for density independent pixels
    //vertical orientation is up and down horizontal is side to side
//padding moves all elements that are inside of a layout into the middle of the layout

    /**log levels determines which messages are shown in the logcat
    verbose shows all available log messages
    debug shows only log messages related to development
    Info shows messages that are for regular usage
    if you select info all of the logs below that are shown along with info logs but the debug logs aren't shown
    if you select debug...the debug logs are shown and all the logs that belong to the levels below
    in general you most likely need the debug filter or the error filter if you only want to filter for errors**/

    /**activity lifecycle is the different states an activity can be in and when it changes
    activity is started - oncreate is called - onstart is called - onresume - activity running - onpause() - onStop() - onDestroy()
    when activity becomes visible to the user onstart() is called
    onresume is called when the activity starts to interact with the user
    onpause is called when an activity goes into the background and loses foreground state. same as clicking to another tab and having other tabs still going in the background
    when the activity gets the users focus again onresume is called again
    onstop is called when the activity is no longer visible to the user so when either a new activity was started from the activity or the activity was destroyed by the system
    the system will kill your app if another app has more priority and needs memory and then you will go back to oncreate
    if you need to save some data you need to do it in onpause because onstop is not guaranteed to be called
    if your app is in the stop state and the user goes back to it then onrestart() is called and then onstart
    ondestroy is called when the app is finishing and when the user presses back button in an activity or destroyed by the system
    whenever starting a new activity it is pushed on top of activity stack**/

    class MainActivity : AppCompatActivity() {
        //MainActivity inherits from AppCompatActivity and takes over the behaviour and makes own activity out of that
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //oncreate automatically inserted and is definited in the AppCompatActivity class
            //oncreate fun is the entry point for the activity
            setContentView(R.layout.activity_main)
            requestPermissions()
        }
        private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED

        //== checks if it's equal to
        //this returns an int that says this permission was accepted or not accepted
        //can refer to all available permissions by writing manifest
        //ActivityCompat checks if the user accepted the permissions already
        private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        private fun requestPermissions() {

            var permissionsToRequest = mutableListOf<String>()
            //whenever you want to request several permissions always need to define the permissions as an string array because all the manifest permission types are strings
            //checks if all the permissions have been accepted
            //this list contains all the permissions the user hasn't accepted before or the permissions he accepted before but then revoked them again
            if (!hasWriteExternalStoragePermission()) {
                permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            }
            if (!hasLocationForegroundPermission()) {
                permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)

            }
            if (!hasLocationBackgroundPermission() && hasLocationForegroundPermission()) {
                permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

            }
            if (permissionsToRequest.isNotEmpty()) {
                Log.d("HDM", "test to see if it goes in here")
                ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
                //permissionsToRequest.toTypedArray() is how you take a mutable list and convert it to a string array
                //checks if the permissions are empty and if it is not we want to request all the permissions in the list
            }

        }
        override fun onRequestPermissionsResult(
            requestCode: Int,
            //requestCode is the same as above which is 0 and is used to differentiate between several requested permissions
            permissions: Array<out String>,
            grantResults: IntArray
            //grantResults: IntArray represents the array that contains those package manager permission granted int
            //you can loop through grantResults and check if a particular permission was granted or not
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            //you can ignore this super implementation since it just means this fun will call the implementation of that fun from the superclass activity
            if (requestCode == 0 && grantResults.isNotEmpty()) {
                //if it equals to 0
                for (i in grantResults.indices)
                //grantResults.indices means it will start at 0 and go through results until it's length is minus 1 so it's the same as grantResults.size - 1
                //requestCode == 0 will check which permissions were accepted by the user and which were not
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        Log.d("PermissionsRequest", "${permissions[i]} granted.")
                        //we looped through the grantResults array and checked for each grant result if it is equal to permission granted so if that particular permission was granted
                        //if it is granted then we know permissions[i] is at the index of i in the permissions: Array<out String>, then we print the permission was "granted"
                    }
            }
        }
        //ctrl o makes you see on list of fun that can be override for every activity
        override fun onPause() {
            super.onPause()
            println("onpause")
        }
    }

    val circle1 = Circle(5.0)
    val circle2 = Circle(3.5)
    val triangle1 = Triangle(4.0, 4.0, 4.0)
    val triangle2 = Triangle(3.0, 3.0, 3.0)
    val rectangle1 = Rectangle(6.0)
    val rectangle2 = Rectangle(4.0, 3.0)

    var integers = (1..10).toList().customFilter { it > 5 }
//.filter works with  every type of list
    var triple = Triple(3, "hello", true)
    var customTriple = CustomTriple<Int, String, Boolean>(3, "hello", true)
    customTriple.printTypes()
    //generic classes are classes with generic type parameters

    var shapes = listOf(circle1, circle2, triangle1, triangle2, rectangle1, rectangle2)
    shapes = shapes.customFilter { it.area() > 20.0 }
        .sortedBy { it.area() } //sort list by the size of area
    for (shape in shapes) {
        println("${shape.name}: Area = ${shape.area()}")
        //for is used since we don't have a fun that prints a shape
    }
    //{it.area() > 20.0 } keeps all the shapes with area greater than 20

//you can use filter fun to filter shapes out as well
//you can apply lamda fun to all lists
}






fun <T> List<T>.customFilter(filterFunction: (T) -> (Boolean)): List<T> {
    //Number is the superclass for number values like double, float, integer
    //<T: Number> is a way to limit the type in a list
    //generics are type parameters that we can pass the type of an object to a fun or class
    //<> tells the list what kind of objects you want to put in it
    //T is naming condition for generic types and can pass any type to the object instead of just <shape>
    //T is a placeholder for any type
    /**Lists<Shape> defines an extension funtion that you can call on a list of shapes
    that extension fun is customFilter
    filterfuntion is the perimeter that takes as a perimeter (Shape) and returns a Boolean
    customFilter fun returns List<Shape>**/

    val resultList = mutableListOf<T>()
    for (item in this) {
        //shift + F6 to rename anything
        //goes through each item in this which is  list
        if (filterFunction(item)) {
            //checks if filterFunction is true
            resultList.add(item)
            //if it is true adds that item to resultList
        }
    }
    //this will loop through the List<Shape> and for each item if filterFunction is true and if it is it's added to the list
    return resultList
    //how to create your own lamda fun
}
//<Shape> extension fun is to make it a lamda fun for shape


/*var list = (1..20).toList()
    //list that consists of numbers from 1 to 20
    println(list)//have to print the list before you filter
    list = list.filter {  it > 8}
    //{it % 2 == 0} checks if it is a even number and if it isn't..it is filtered out
    //filter function is a lamda fun and filters out numbers that aren't true with the statement like i > 8
    //you can recognize a lamda fun when there are {} in the menu
    //it refers to current number


//lambda functions are used to pass functions as parameters to other functions
    //lamda fun you can call fun that already exist but provide own implementation of how to filter
//ctrl p is used to see what to put inside of any {} ()
println(list)*/


/*val division = try{
    divide(5.0, 0.0)
} catch (e: DivisionByZeroException){
    0.0
}
//doesn't crash since try and catch statement was used
println("The result of the division is $division")
// println(divide(5.0,0.0))
//you can write the try catch without val division =
println("Please enter a number:  ")
val input = try {
//try means that kotlin will try to execute this piece in {}
readLine()?.toInt()
//if there are exceptions aka errors in the try block it will jump to catch block
} catch (e : Exception){
//exception like NumberFormatException is a class that inherits from the main class exception
//e : Exception will catch all the exceptions as every class inherits from that superclass exception
//NumberFormatException is thrown if the string is not a valid representation of a number and is used to handle specific exceptions
//how to create own exceptions
0
}finally {
println("this is from the finally block")
//finally will be executed in all cases no matter what
}
println("You entered $input")*/
//specify the type of exception

//how to convert a word to a number


//exceptions is an event that occurs during the execution of a program and that event disrupts the normal flow
// how to detect errors at runtime
//when exceptions happen..it will crash our program if we don't implement proper error handling


/*  val a = 3.0
  val b = 4.0
  val height = 2.0
  val parallelogram = object :Shape("Parallelogram",a,b,height){
      //object: creates a anonymous class that inherits from shape and you can modify that doesn't look like the shape class

      /** anonymous class **/
     init {
          println("Parallelogram created with a = $a, b = $b and height = $height")
          println("The area is ${area()}")
          println("The perimeter is ${perimeter()} ")
          //so you can access all the functions that are in shape class or this anonymous class
      }
      override fun area(): Double {
          return a * height
//how the object area is calculated
      }

      override fun perimeter(): Double {
return 2 * a + 2 * b
      }
      fun isRectangle(): Boolean = height == b
    //  returns if height is equal to side b
      //checks if the parallelogram is a rectangle
      //changes the shape class a little
  }

//example of instances with abstract class : val context = Context(
//can't create instances of an abstract class

//an anonymous class is an implementation of a class with a slight modification but you don't have to create a new class

  println("Is the parallelogram = rectangle? ${parallelogram.isRectangle()}")

  //val circle = Circle.randomCircle()
  //how you access the companion object in circle class
  //circle() is for use with specific radius

  //fun inside companion objects and inside specific classes are different b
*/

/* val rect = Rectangle(5.0)
  //constructors provides different ways to create a rectangle since we created several constructors in Rectangle class
  val circle = Circle(3.0)
  val triangle = Triangle(7.0,7.0,7.0)

val maxAreaRectandCircle = maxArea(rect,circle)
  val maxAreaRectCircleTriangle = maxArea(rect,circle,triangle)
  //calculate the max area out of the three shapes

  println("The maximum area of rectangle and the circle is $maxAreaRectandCircle")
  println("The maximum area of rectangle, the circle, and the triangle is $maxAreaRectCircleTriangle")
*/

/* val myCircle = Circle(5.0)
//has the name Circle because we set the name in the constructor of the shape class in circle class

myCircle.changeName("Peter")
//inherits the function changeName from the shape class through the circle class
println("The name of the circle is ${myCircle.name}")

val myTriangle = Triangle(2.0, 3.0, 5.0)
myTriangle.changeName("Susan")
println("The name of the triangle is ${myTriangle.name}")*/


/*val myRect = Rectangle(4.0,7.0)
println("Rectangle area is ${myRect.area()}")
    println("Rectangle perimeter is ${myRect.perimeter()}")
    println("Is Rectangle a square? ${myRect.isSquare()} ")

val myCircle = Circle(5.0)
    val myCircle2 = Circle(7.0 )*/
//myc1 and myc2 are instances of the circle class
//Circle() is the blueprint and of the blueprint you can create objects as myc1 and myc2


/* val x: Int = 0
val y: Int = 5
x.plus(y)*/
//x or y is an object and the class of the object is Int or String
//classes are like blueprints for objects


/* println("Please enter a number")
 val input = readLine()?.toInt()

 if (input != null) {
    // input.isPrime()
     //how to extend the fuctions

     if (input.isPrime()){
         println("$input is a prime number")
     } else{
         println("$input is not a prime number")
     }
 }*/
//}


class DivisionByZeroException : Exception("you cannot divide by zero, choose different number")

//DivisionByZeroException is an exception for if one of the perimeters is 0
fun divide(a: Double, b: Double): Double {
    if (b == 0.0) {
        throw DivisionByZeroException()

    }
    return a / b
    //if you divide and the second number is equal to 0 division won't work since you can't divide by 0

}

fun maxArea(shape1: Shape, shape2: Shape): Double {
    //perimeters are shape1 and shape2 and it returns a double since the area in shape class are doubles
    val areaShape1 = shape1.area()
    val areaShape2 = shape2.area()
    return if (areaShape1 > areaShape2) areaShape1 else areaShape2
    //checks if the areashape1 is greater than areashape2 if it is return shape1...if not return shape2
}

fun maxArea(shape1: Shape, shape2: Shape, shape3: Shape): Double {
    val maxAreaShape1Shape2 = maxArea(shape1, shape2)
    //we can use the functions that returns the max number out of two shapes above for this one
    val areaShape3 = shape3.area()
    return if (maxAreaShape1Shape2 > areaShape3) maxAreaShape1Shape2 else areaShape3
    /**shorter return statement return maxArea(maxArea(shape1,shape2),shape3)**/
}
//checks which shape has the bigger area and returns that area
//this overloads the functions with two or three shapes and get the max area


fun Int.isPrime(): Boolean {
    //how to access the input in extension functions
    for (i in 2 until this - 1)
    //i in 2 means that if i = 7 then you will divide by 2,3,4,5,6 but not 7 because -1
    //Prime numbers are natural numbers that are divisible by only 1 and the number itself.
        if (this % i == 0) {
            // 8 % 2 = 0 because 8 is divisible by 2
            return false
            //if you find a number that 7 is divisible by then the boolean is false because then 7 is not a prime number
            // if 7 is divisible by i and then returns the remainder
            //if you have 7 % 3 then 3 fits 2 times into 7 so 3 % 2 = 6 the remainder from 6 to 7 is 1
        }
    return true
    //if we haven't found a number that input is divisible by then it is true

    /* val max = getMax(1,2,3,4,5,10,6,8)
     println("The maximum is $max")
     val array = intArrayOf(10,20,25)

 searchFor(searchEngine = "Bing",search = "how to become the best Kotlin programmer")
     //named parameters are used if you have alot of parameters and you don't know which order to put them in
     //you don't have to have the named parameters in order as well

     searchFor("how to become a good programmer")
     //you don't need to specify the search engine string as it is google by default
     //searchFor("how to become the best Kotlin programmer", "Bing")
     //here is how you change the search engine to what you want*/
}

fun searchFor(search: String, searchEngine: String = "Google") {
    println("Searching for $search' on $searchEngine")
//fun that searches for a specific string on the internet
// searchEngine: String specifies which search engine you want to use to search for the string


    /*  val max = getMax(1,2,3,*array,4,5,10,6,8)
    //how to insert the numbers inside of an array in between parameter list above
    println("The maximum is $max")*/
}

/*fun getMax(vararg numbers: Int): Int {

   var max = numbers[0] //sets max to the first value of the numbers
   for (number in numbers) {
       //checks for each number in a loop
       if (number > max)
       //compare each number to see if it's greater than our maximum
       {
           max = number
           //sets the current number as max
       }


   }
   return max

   //return the maximum of a sequence of numbers
   // can have a variable number of arguments of parameters that are are saved in numbers and are all in type int


   /* printPow(3,5)
    printPow(5, 2)
    printPow(7,8)
    printPow(2, 10)

    println(multiply(3,5))*/
}*/

fun printFirstItem(list: List<Int>) = println(list[0])
//how to pass a list as a parameter

fun multiply(a: Int, b: Int) = a * b
//don't need to specify the type after () as kotlin will recognize it is an integer again
//how to write a function in one line

fun printPow(base: Int, exponent: Int): Int {
    var result = 1  //stores the result of each calculation
    for (i in 1..exponent)
        result *= base
    //how you calculate the base to the power of the exponent
    //can also be written as result = result * base
    //*= is multiplying...if you don't have the = sign it won't save the result of the multiplication in result
    //base and exponent are parameters
    //the fun calculates first number to the power of the second number and print the result
    println("$base to the power of $exponent is $result")
    //means that you multiply 3 by itself 5 times
    return result
    //return means that it will cancel the fuction so anything you code after will not be executed
//when you want to return a value in the function you have to specify the type of the value after () :Int
}

/*fun printThreeLines(){
    println("First")
    println("Second")
    println("Third")
}*/
//how to access a variable in multiple functions by attaching parameters
//create a fun that you can attach two values

/* val age = readln()?.toInt()

    when(age){
        in 0..5 -> println("You're a young kid")
        //you can't insert another println command without -> {}
        in 6..17 -> println("you're a teenager")
        18 -> println("you are 18")
        19,20 -> println("You're a young adult")
        in 21..65 -> println("You're an adult")
        else -> println("You're really old")
        //you can have strings where the numbers are as well
    }
    //when simplifies some types of if statements or if conditions
    //only one variable can be used in when statements
*/


/* val list = mutableListOf<Int>()
  for (i in 1..10) {
      val x = readln()?.toInt()
      if (x != null) {
          list.add(x)
      }
      //accept 10 user inputs and prints them out as typed
      //this is only possible with mutable lists not arrays as you can add numbers during runtime
  }

for(i in list) {
  //this works exactly the same as it does below with arrays
}
  //println(list)

  listOf(1,2,3)
  println("the numbers in reverse")
  for (i in list.size - 1 downTo 0){
      println(list[i])
  }
  //the size of the list is 3 so you subtract it by -1 since you are printing the index list[i] indexes start at 0 not 1


  //you have to enter the type of list like an int when the list is empty
  //this works only with a list and not an array
  //the user enters the numbers at runtime so it won't work with arrays


 */
/* val list = mutableListOf(1,2,3)
 list.remove(3) //can't remove a number that isn't already on the list
 list.removeAt(1)//takes the index not the element and removes the number 2
 //when you take an index it starts counting at 0,1,2
 list.add(4) //adds value during runtime
 println(list)*/


//lists are similar to arrays but more flexible, the values of an array can be changed during runtime of app while a list's value can't
//mutable means the values can be changed
//the difference is mutable lists can even change in size
//arrays can't call the same methods like add or removeAt like mutable lists

/* val arayay = arrayOf(4,5,8,9,10,2,3)
var max = arayay[0]
 for (i in arayay){
     if (i > max){
         max = i
     }
     println(max)
 }*/
//how to get the largest number out of an array
//it will loop and check if each item is larger than the next


/* for (i in 'a'..'z'){
     println(i)
 }
//even simpler way of writing the for loop below
//1..3 is going from 1 to 3 and 3 downTo 1 is how to go in reverse
// example :i in 20 downTo 1 step 2 // step is counting by what step equals to
//with for loops and ranges you can print the entire alphabet
val myar = arrayOf(1,2,3)
 for (i in myar){
     println(i)
 }*/
//for loop does the same thing as while loop in a shorter and more efficient way

/* val myarr = arrayOf("hello","guys", "whats up?")
 val arraylength = myarr.size
 //size will return how many elements are in the array
 var i = 0
 while (i < arraylength){
     println(myarr[i])
     i++
 }*/

/*var x = 3
    while (x > 0){
        println("hello")
    x--
    }*/
//so hello is printed 3 times since while statement goes through a loop until it's false 3 > 0, 2 > 0, 1 > 0
//x-- just means that x will be decreased by one
//while is used to execute certain code over and over again like a loop
//only if while is false the program will continue to the next lines of code

/*
val myarray = arrayOf("hello","guys", "whats up?")
println(myarray[2])
//arrays are a way to say multiple objects inside one object, arrayOf() creates an array
//arrays can contain strings and numbers
//[] is how you access single objects in arrays*/

/*  val userInput = readLine()?.toInt()
   if (userInput != null) {
       println(userInput - 5)
       //no question mark before the dot now because we now know it won't be null
   }*/
//else if (userInput == String()) println(userInput?.toInt().toString())


//.toInt() converts the variable into a number
//readLine() will always return as a string? which is an empty object
//String? means the return string can be null
/*
    val x = 8
    val y = 7
    val z = if (x + y == 14) 3   else 4
        //if () checks if a certain condition is true and if it is true assign 3 as the result
    if (x < y || y * y == 49)
        println("one of the conditions is true")
     else if (x > y)
        println("x is greater than y")
     else
        println("x is equal to y")

    println("the value of z is $z ")
    //when you have only one command then you don't need the curly brackets
//you can have as many else if conditions as you want
    //palindrone is a string that's read backwards the same as it's read forward.  example : racecar


    val f = 5
    val g = 5

    val a = 6
    val b = 10
    println((f == g || a == b && a > g))

   val bool = true
    val c = 9
    val d = 3
    val e = 9
    val hardExpression = c == e && bool || e > (c + d) && (!bool || d < e)
    println(hardExpression)
*/
//just like in regular math the result will be the multiplication then addition even though addition is written first. same with && being first and || being second even though it's written first
//(()) is used if you want the result to go in order
//!() means negates the boolean which means it's the opposite
//&& is used to check if both conditions are true in a single expression
//the && result will only be true if both conditions are true
//|| is used if you want to see if at least one of the conditions are true
//== is used to check if the two variables have the same value
//LOGICAL AND COMPARISON OPERATORS always give a boolean answer true or false

//&& Operator will not check the second expression if the first expression is false, because false && anything = false
//|| Operator will not check the second expression if the first expression is true, because true || anything = true

/*val string = "haamid"
println("Our string is: ${string.uppercase().reversed()}")
//${} is to call functions on string
//() means that it can be a string as well
var x = 20
var y = 8
println("The result of x + y is: ${x + y}")
x += 10
//x = x + 10 is alternate way of writing x+= 10
println("The result of x + y is now: ${x + y}")
//${} to print the whole expression of x + y in a string
//+= means adds the result to the value of x
//replacing + with all ARITHMETIC OPERATORS works as well

var a = 20.2945894033849
var b = 8.0
println("The result of a / b is: ${a / b}")
//adding F to the whole number tells the computer you want to calculate decimal numbers for division
//decimal numbers in kotlin are called floating numbers so write float for type
//double is another way to calculate the result of decimals more accurately. simply remove the F
//the reason you use float instead of doubles is because your computer needs more memory to save a double number
//if you need the number to be 100% accurate you can use a double number anyway though
newtutorial()
newtutorial2()
newtutorial3()
val instagramBio: String? = "growth"
// null means having no value, if a variable is allowed to have no value then update the type to include ? which is nullable String
if (instagramBio != null) {
    println(instagramBio.uppercase())
}
println(instagramBio?.uppercase())
// ?. it means will only call this method on this variable if it's non null

val firstname = "Haamid"
//val is for read-only
var weight = 160
//var is for read and write. if it can change use instead of val
val myvariable: Double = 2.5
//double is for decimals
val myvariable2: Boolean = true
//Booleans are either true or false

// I am a comment! I am ignored by the computer

val s1 = "Call me"
val s2 = " maybe"
val combined = s1 + s2
println(combined)

val n1 = 9
val n2 = 4
val result = n1 % n2 //ARITHMETIC OPERATORS: - subtract * multiply / divide to the number before the dot % remainder after division
println(result)



val myString = "Kotlin"
println(myString[0])
println(myString[1])
//how to choose a letter out of the string with 0 being the first letter

println(myString.isEmpty()) //check if string is empty which returns boolean true or false
println(myString.length) //get the length of the string. result is the number of how many letters is in the string
println(
    myString.substring(
        2, 4
    )
)
//extract a portion of the string between the start and end index. not including the last index

println("The string is $myString") //$ is used to print out the value of the variable inside the string.

val examScore = 55
if (examScore > 70) {
    println("you passed!")
} else {
    println("you failed :(")
}

// if will evaluate whether a statment is true or false and execute code only if it's true
// >= greater than or equal to, <= less than or equal to, == equal to, != not equal to


val name1 = "Haamid"
val name2 = "Jamar"
val name3 = "Saleem"

val names = listOf("Haamid", "Jamar", "Saleem")
println(names[2])

// how to hold all variables at the same time. 0 is name 1 and so forth.

val names1 = mutableListOf("Haamid", "Jamar", "Saleem")
println(names[2])
names1.add("Lateefah")

//by default you can't add or remove names from the list so you have to make it mutableListOf
//all elements in a collection must have the same type, you could make it explicit by adding <String>

val namess = mutableListOf("Haamid", "Jamar", "Saleem")
for (name in namess) {
    println(name)
}
//for is if you want to print out each element in order in the list of brothers names

for (i in 1..5){
println(i)

}
//for can also be used if you want to do something a certain amount of times, .. is how many times you want to execute
for (ii in 1 until 5){
    println(ii)
//until if you don't want to include the last number
}
myfunction("jay")

}

//can't call any methods on something that is null. this is how you check if it is not null
fun myfunction(name : String) {
println("hello, $name")
//functions are a way to combine chunks of code so you can reuse them
//make private so it can't be accessed by other classes or files
}
//println() stands for print line in console

fun newtutorial (){
var myVariable = 5
println(myVariable)
}
//var myVariable is called camel case since the first word is lowercase
//int is a type for a variable that is for numbers
//kotlin compiler is smart enough to determine the type of the variable if it sees the value. you don't have to specify the type.
fun newtutorial2 (){
var myVariable = 5
myVariable = 1
println(myVariable)
//you can assign a new value to the variable if you set it to a different value underneath the first variable
}
fun newtutorial3 (){
var myVariable = 5
println("The value of our variable is: $myVariable")
myVariable = 1
println("The value is now: $myVariable")
*/
//}