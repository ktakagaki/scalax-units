package scalax.test

import scalax.units.Units._

object Issue001 {
  val accel0: Acceleration[Double] = (10.0 m) / ((2.0 s) * (1.0 s))
  val accel1: Acceleration[Double] = accel1 / (1.0 m) * (2.0 m)
  val accel2: Acceleration[Double] = accel2 * (1.0 m) / (2.0 m)
}
