package com.innerproduct.ee.effects

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

object Timing extends App {
  // val clock: MyIO[Long] = MyIO(() => Instant.now.getEpochSecond)
  val clock: MyIO[Long] =
    MyIO(() => System.currentTimeMillis())

  def time[A](action: MyIO[A]): MyIO[(FiniteDuration, A)] =
    for {
      start <- clock
      act <- action
      end <- clock
      duration = FiniteDuration(end - start, TimeUnit.MILLISECONDS)
    } yield (duration, act)

  val timedHello = Timing.time(Printing.hello)

  timedHello.unsafeRun() match {
    case (duration, _) => println(s"'hello' took $duration")
  }
}
