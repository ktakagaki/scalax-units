package scalax.units.test

object UnitsTest {
  import scalax.units.Units._
  
  val dist: Length[Double] = 2.3 m
  val time: Time[Double] = 1.7 s
  val speed: Speed[Double] = dist / time
}
