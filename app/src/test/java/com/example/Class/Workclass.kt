package com.example.Class

//class Workclass {

    fun main(){
        //Numeric variables
        val age:Int =20
       // val long_number:Long = 789123128937
        //val temperature:Float = 27.33f
        //val weight:Double = 64.1231

        //String variables
        //val gender:Char = 'M'
        val name:String = "Roberto Bravo"

        //Bool variables
        //val isGreater:Boolean = false

        //Collection variables
        val names = arrayOf("Erick", "Sofia", "Javier", "Veronica")
        println(age)
        println("Welcome to $name, to first Kotlin project ")
        println(add())
        println(product(5,8))
        printarray(names)
        val numers = arrayOf(1,2,3,4,5,6,7,8,9,10)
        isEven(numers)
        println(getDay(1))
        val person = person("Roberto", 21)
        person.displayInformation()
        println(person.name)
        println(person.age)
    }

    fun add():Int{
        val x=10
        val y=5
        return(x + y)
    }

    fun product(x:Int, y:Int):Int{
        return(x + y)

    }
    fun printarray(names:Array<String>){
        println(names)
        for(name in names){
            println("hello $name")
        }
    }

    fun isEven(numbers:Array<Int>){
        for(number in numbers){
            if(number % 2 == 0){
                println("the number $number is even")
            }else{
                println("the number $number is odd")
            }
        }
    }
fun getDay(day:Int):String{
    var name = ""
    when(day){
        1 -> name = "Monday"
        2 -> name = "Tuesday"
        3 -> name = "Wednesday"
        4 -> name = "Thursday"
        5 -> name = "Friday"
        6 -> name = "Saturday"
        7 -> name = "Sunday"
        else -> name = "Incorrect value"
    }
    return name
}

class person(val name:String, val age:Int){
    fun displayInformation(){
        println("Name: $name Age: $age")
    }
}
//}