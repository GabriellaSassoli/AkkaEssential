package part1Recap

import scala.concurrent.Future

object ThreadModelLimitation extends App {

  /**
   * DR 1: OOP encapsulation is only valid on the SINGLE THREADED MODEL.
   * */

  class BankAccount(private var amount: Int){ //at volatile allows only 1 threads.
    override def toString: String = "" + amount
    def withdraw(money: Int) = this.synchronized{ this.amount -= money}
    def deposit( money: Int) = this.synchronized{this.amount += money} //thread sage because only one thread can pick this up
    def getAmount = amount
  }

//  val account = new BankAccount(2000)
//  for(_ <- 1 to  1000) {
//    new Thread(() => account.withdraw(1)).start()
//  }
//
//  for(_ <- 1 to  1000) {
//    new Thread(() => account.deposit(1)).start()
//  }
//  println(account.getAmount)

  //OOP encapsulation is broken in a multithreaded env

  //synchronization

  //deadlocks,livelocks

  /**
   * DR 2: delegating something to a thread is a PAIN.
   * */

  //you have a running thread an you want to pass a runnable to that thread

  var task: Runnable = null
  val runningThread: Thread = new Thread(()=> {
  while (true){
    while(task == null){
      runningThread.synchronized{
        println("[background] waiting for a task.. ")
        runningThread.wait()
      }
    }
    task.synchronized{
      println("[background] I have a task")
      task.run()
      task = null
    }
  }
  })

  def delegateToBackgroundThread(r:Runnable) = {
    if (task == null) task = r
    runningThread.synchronized{
      runningThread.notify()
    }
  }

  runningThread.start()
  Thread.sleep(1000)
  delegateToBackgroundThread(() => println(42))
  Thread.sleep(1000)
  delegateToBackgroundThread(() => println("this should run in the background"))

/**
 * DR 3: Tracing and dealing with errors in a multithreaded env is a pain
 * */
  //1M numbers in between 10 threads
  import scala.concurrent.ExecutionContext.Implicits.global
  val futures = (1 to 9)
    .map( i => 100000 * i until 100000 * ( i + 1))
    .map(range => Future{

      if ( range.contains(231564)) throw new RuntimeException("invalid number")
      range.sum
    })

  val sumFuture = Future.reduceLeft(futures)(_ + _)
  sumFuture.onComplete(println)

}
