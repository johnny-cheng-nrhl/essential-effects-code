import cats.effect._
import cats.implicits._

//// Constructors ////

// delay
IO.delay(1 + 2) // Delay(<function0>; IO.delay(TimeIntensive)
// apply
IO(1 + 2)
IO.apply(1 + 2)
// IO[Int](throw new RuntimeException("Blah!")).unsafeRunSync()
// pure
IO.pure("hi")
// raiseError
IO.raiseError[Int](new RuntimeException("meh!"))

// From eric@peters.org or tucows
//IO.succeed() I think  is  the equivalent in ZIO, right? IO is wrong type for error
// foldRight

//// Eliminators, do not ever use in normal code! ////

// unsafeRunSync
// unsafeToFuture

res2.unsafeRunSync()
//// Combinators ////

// map
res2.map(_ + 1).unsafeRunSync()
// as
res2.as(42).unsafeRunSync()
// void
res2.void.unsafeRunSync()

// mapN
(IO(13), IO("hi")).mapN((i, s) => (i, s))
// tupled
(IO(13), IO("hi")).tupled
// *>
(IO(print("12")) *> IO("hi")).unsafeRunSync()
// res20 String = "hi"
// <*

// flatMap
// flatTap
IO(12).flatTap(i => IO(println(i))).unsafeRunSync
// >>

// attempt
// Make the thing safe


// adaptError
// handleErrorWith
// onError
// recover
// recoverWith
// redeem
// redeemWith

// guarantee
// guarantee this thing run at the end
// guaranteeCase
// onCancel
