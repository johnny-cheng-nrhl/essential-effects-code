package com.innerproduct.ee.effects

case class MyIO[A](unsafeRun: () => A) {
  def map[B](f: A => B): MyIO[B] =
    MyIO(() => f(unsafeRun()))

  def flatMap[B](f: A => MyIO[B]): MyIO[B] = {
    // f(unsafeRun()) This is eager
     MyIO(() => f(unsafeRun()).unsafeRun())
  }

  // Challenge can you make map with flapMap and pure?

} // <1>
//case class MyIO[A](f: () => A) {
//  // def unsafeRun(): A = f()
//
//
//}

object MyIO {
  def putStr(s: => String): MyIO[Unit] =
    MyIO(() => println(s))
}

object Printing extends App { // <3>
  // This is already lazy; lazy keyword not necessary
  lazy val hello = MyIO.putStr("hello!")
  println("world")
  val delayed = MyIO(() => Thread.sleep(1000))

  hello.unsafeRun()
  delayed.unsafeRun()
  hello.unsafeRun()
}