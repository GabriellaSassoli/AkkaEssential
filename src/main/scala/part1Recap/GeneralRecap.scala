//package part1Recap
//
//import scala.reflect.runtime.universe.Try
//
//class GeneralRecap extends App{
//
//  val aCondition: Boolean = false
//
//  var aVariable = 42
//  aVariable +=1
//
//  //expressions
//  val aConditionedVal = if (aCondition) 42 else 65
//
//  //codeblock
//  val aCodeBlock = {
//    if (aCondition) 74
//    else 56
//  }
//
//  //types
//  //Unit
//  val theUnit = println("Hello Scala")
//
//  def aFunction(x: Int) = x + 1
//
//  //recursion - TAIL RECURSION
//  def factorial(n:Int, acc:Int): Int =
//    if (n<= 0 ) acc
//    else factorial(n - 1, acc * n)
//
//  //OOP
//
//  class Animal
//  class Dog extends Animal
//  val aDog:Animal = new Dog
//
//  trait Carnivore{
//    def eat(a:Animal): Unit
//  }
//
//  class Crocodile extends Animal with Carnivore{
//    override def eat(a:Animal): Unit = println("crunch!")
//  }
//
//  //method notations
//  val  aCroc = new Crocodile
//  aCroc.eat(aDog)
//  aCroc eat aDog
//
//  ///anonymous classes
//  val aCarnivore = new Carnivore {
//    override def eat(a: Animal): Unit = println("roar")
//  }
//
//  //generics
//  abstract class MyList[+A]
//  //companion object - singleton objects
//  object MyList
//
//  //case class
//  case class Person(name:String,age:Int)
//
//  //exception
//  val aPotetentionFailure = try{
//    throw new RuntimeException("I'm innocent, I swear") //Nothing
//  }catch{
//    case i:Exception => "I cought an execption"
//  }finally{
//    //side effect
//    println("some logs")
//  }
//
//  //functional programming
//
//  val incremental = new Function1[Int,Int]{
//    override def apply(v1: Int): Int = v1 + 1
//
//    val incremented = incremental(42)
//
//    val anonymousIncremental = (x:Int) => x + 1
//
//    List(1,2,3).map(incremental)
//
//    //for-comprehensions
//    val pairs = for{
//      num <- List(1,2,3,4)
//      char <- List('a','b','c','d')
//    }yield num + "-" + char
//  }
//
//  //List(1,2,3,4).flatMap(num => List('a','b','c','d').map( char => num + "-" + char
//
//  //Seq,Array,List,Vector,Map,Tuples,Sets
//
//  //collections
//  val anOption = Some(2)
//  val aTry = Try{
//    throw new RuntimeException
//  }
//
//  //pattern matching
//  val unknown = 2
//  val order = unknown match{
//    case 1 => "first"
//    case 2 => "second"
//    case _ => "unknown"
//  }
//
//  val Bob = Person("bob",22)
//  val greeting = Bob match{
//    case Person(n,_) => s"Hi my name is $n"
//    case _ => "I don't know my name"
//  }
//
//  //all the patterns
//
//
//}
