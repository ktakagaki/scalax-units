package scalax.example

import scalax.units.Units.Acceleration
import scalax.units.Units.Area
import scalax.units.Units.Length
import scalax.units.Units.Mass
import scalax.units.Units.Temperature
import scalax.units.Units.Time
import scalax.units.Units.Speed
import scalax.units.Units.Volume
import scalax.units.Units.measure
import scalax.units.Units.numericToQuantity

  /*
   *  - unit names as implicit methods
   *  - no standalone units like "m", "1 m" necessary
   *  - no weak conformance between numbers, e. g. Time[Int] will always stay an Int
   *  - many parens necessary
   *  - "number * Quantity" doesn't work, only "Quantity * number"
   *  - no unit information in toString, use niceString (and provide an implicit in scope, if necessary)  
   */

object UnitExample extends App {
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
  
  println(mass.niceString)
  println(length.niceString)
  println(temperature.niceString)
  println(time.niceString)
  println(time2.niceString)
  println(time3.niceString)
  println(speed.niceString)
  println(area.niceString)
  println(volume.niceString)
  println(smallVolume.niceString)
  println(accel.niceString)
}