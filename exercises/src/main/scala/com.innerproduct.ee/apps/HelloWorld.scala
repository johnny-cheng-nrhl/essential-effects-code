package com.innerproduct.ee.apps

import cats.effect._

object HelloWorld extends IOApp { // <1>
  // Need to have the run method that returns an IO
  // like a main program!!
  def run(args: List[String]): IO[ExitCode] = // <2>
    helloWorld.as(ExitCode.Success) // <3> Don't care what helloWord is as long as it is type IO

  val helloWorld: IO[Unit] =
    IO.raiseError(new RuntimeException("meh"))
    // IO(println("Hello world!"))
}