package part1Recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MultithreadingRecap extends App{

  //creating threads on the jvm

  val aThread = new Thread(new Runnable{
    override def run(): Unit = print("I'm running in parallel")
  })

  //equivalent
  val aThread2 = new Thread(() => println("I'm running in parallel"))
  aThread2.start()
  aThread2.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))

  threadHello.start()
  threadGoodbye.start()

  //different runs produce different results!

  class BankAccount(@volatile private var amount: Int){ //at volatile allows only 1 threads.
    override def toString: String = "" + amount
    def withdraw(money: Int) = this.amount -= money
    def safeWithdraw( money: Int) = this.synchronized{
      this.amount -= money //thread sage because only one thread can pick this up
    }
  }// not thread safe

  /*
  BA( 10000)

  Not ATOMIC -> not thead safe because with 2 different threads for 2 different withdraws we could have the wrong final money in the account.
  Need to add sincronous instructions


  //inter-thread comunivacion on the JVM
  //wait - notify mechanism
   */

  //scala Futures
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future{
    42
  }

  //calbacks
  future.onComplete{
    case Success(42) => print("I found the meaning")
    case Failure(exception) => println("Something failed")
  }

  val aProcessedFuture = future.map(_ + 1) // Future 43
  println(aProcessedFuture)
  val aFlatFuture = future.flatMap{value => Future( value + 2)} // Future 44
  println(aFlatFuture)
  val filterFuture = future.filter(_% 2 == 0 ) // no such element exception
  println(filterFuture)

  //for comprehensions
  val aNonSenseFuture = for{
    meaningOfLife <- future
    filteredMeaning <- filterFuture
  }yield meaningOfLife + filteredMeaning

  //promises
}
