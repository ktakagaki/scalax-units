package scalax.units.example

import scalax.units.Units._
import scalax.units.Integers._

  /*
   *  - unit names as implicit methods
   *  - no standalone units like "m", "1 m" necessary
   *  - no weak conformance between numbers, e. g. Time[Int] will always stay an Int
   *  - many parens necessary
   *  - "number * Quantity" doesn't work, only "Quantity * number"
   *  - no unit information in toString, e. g. no "Quantity(5m)", just "Quantity(5)"  
   */

object UnitExample2 extends App {
  val mass: Mass[Double] = 4.0 kg
  val longMass: Mass[Long] = (4.0 kg) asLong
  val length: Length[Int] = (5 m) * 3
  val time: Time[Int] = 1 s
  val time2: Time[Int] = time + time
  val time3 = time2 + (1 minute)
  val temperature: Temperature[BigDecimal] = BigDecimal("22.22") k
  val speed: Speed[Int] = length / time
  val area: Area[Int] = length * length
  val volume: Volume[Int] = (23 m) * (1 m) * (1 m)
  val smallVolume = (volume asDouble) / 12.0
  val area2: Area[Int] = volume / (23 m)
  val accel: Acceleration[Double] = (10.0 m) / ((2.0 s) * (1.0 s))
  val accelNum: Double = accel toDouble
  
  println(mass)
  println(length)
  println(temperature)
  println(time)
  println(time2)
  println(time3)
  println(speed)
  println(area)
  println(volume)
  println(smallVolume)
  println(accel)
  println(accelNum)
}