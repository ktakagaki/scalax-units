package scalax.example

import scalax.units.Units._

  /*
   *  - unit names as implicit methods
   *  - no standalone units like "m", "1 m" necessary
   *  - no weak conformance between numbers, e. g. Time[Int] will always stay an Int
   *  - often either parentheses (2.0 m) * ... or dots 2.0.m * ... are necessary
   *  - "number * Quantity" doesn't work, only "Quantity * number"
   *  - no unit information in toString, use unitString (and provide an implicit value in scope, if necessary)
   */

object UnitExample extends App {
  val mass: Mass[Double] = 4.0 kg
  val longMass: Mass[Long] = (4.0 kg) asLong
  val length: Length[Int] = (5 m) * 3
  val time: Duration[Int] = 1 s
  val time2: Duration[Int] = time + time
  val time3 = time2 + (1 minute)
  val temperature: Temperature[BigDecimal] = BigDecimal("22.22") k
  val speed: Speed[Int] = length / time
  val area: Area[Int] = length * length
  val volume: Volume[Int] = (23 m) * (1 m) * (1 m)
  val smallVolume = (volume asDouble) / 12.0
  val area2: Area[Int] = volume / (23 m)
  val accel: Acceleration[Double] = (10.0 m) / ((2.0 s) * (1.0 s))
  val accelNum: Double = accel toDouble

  println(mass.unitString)
  println(length.unitString)
  println(temperature.unitString)
  println(time.unitString)
  println(time2.unitString)
  println(time3.unitString)
  println(speed.unitString)
  println(area.unitString)
  println(volume.unitString)
  println(smallVolume.unitString)
  println(accel.unitString)
}