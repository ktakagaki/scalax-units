# scalax-units

Units of Measurements for Scala
based on Jesper Nordenberg's [metascala](http://www.assembla.com/spaces/metascala/wiki)
and Erik Osheim's [Numeric work](https://github.com/azavea/numeric) 

Example code:

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
  