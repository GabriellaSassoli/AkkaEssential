//package part1Recap
//
//import scala.concurrent.Future
//
//object AdvancedRecap extends App{
//  //partial functions
//  val partialFunction:PartialFunction[Int,Int] = {
//    case 1 => 42
//    case 2 => 65
//    case 5 => 999
//  }
//
//  val pf(x:Int) => x match{
//    case 1 => 42
//    case 2 => 65
//    case 5 => 999
//  }
//  val function: (Int => Int) = partialFunction
//
//  val modifiedList = List(1,2,3).map{
//    case 1 => 42
//    case _ => 0
//  }
//  //lifting
//  val lifted = partialFunction.lift //total fuction Int => Option{Int]
//  lifted(2)// Some(65)
//  lifted(5000) //None
//
//  //orElse
//  val pfChain = partialFunction.orElse[Int,Int] {
//    case 60 => 90000
//  }
//
//  pfChain(5) //999
//  pfChain(60) ///90000
//  pfChain(457)// error
//
//  // type aliases
//  type ReceiveFunction = PartialFunction[Any,Unit]
//
//  def receive:ReceiveFunction ={
//    case 1 => println("hello")
//    case 2 => println("something else")
//  }
//
//  //implicits
//  implicit val timeout = 3000
//  def setTimeout(f:() => Unit)(implicit timeout:Int) = f()
//  setTimeout(() => println("timeout"))
//
//  //implicit conversionst
//  //1) implicit defs
//  case class Person(name:String){
//    def greet = s"Hi, my name is $name"
//  }
//
//  implicit def fromStringToPerson(string: String):Person = Person(string)
//
//  "Peter" greet //fromstringtoPerson and then greet
//
//  //2) implicit classes
//  implicit class Dog(name:String){
//    def bark =println("bark")
//  }
//  "Lassie".bark
//
//  //organize
//  //local scope
//  implicit val inverseOrdering: Ordering[Int] =  Ordering.fromLessThan(_ > _)
//  List(1,2,3).sorted
//
//  //imported scope
//  import scala.concurrent.ExecutionContext.Implicits.global
//  val future = Future{
//    println("Hello, future")
//  }
//
//  //companion objects of the types included in the call
//  object Person{
//    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name)< 0 )
//
//  }
//
//  List(Person("Bob"), Person("Alice")).sorted
//}
