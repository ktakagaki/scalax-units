package scalax.units

import scala.language.implicitConversions

object Units {
  import spire.math.Numeric
  import spire.math.Numeric._
  import Integers._
  import Addables._
  import Subtractables._

  implicit val postfixOps = scala.language.postfixOps

  trait Unit {
    type M <: MInt
    type KG <: MInt
    type S <: MInt
    type A <: MInt
    type K <: MInt
    type Mol <: MInt
    type CD <: MInt
  }

  final class TUnit[_M <: MInt, _KG <: MInt, _S <: MInt, _A <: MInt, _K <: MInt, _Mol <: MInt, _CD <: MInt] {
    type M = _M
    type KG = _KG
    type S = _S
    type A = _A
    type K = _K
    type Mol = _Mol
    type CD = _CD
  }

  case class Quantity[M <: MInt, KG <: MInt, S <: MInt, A <: MInt, K <: MInt, Mol <: MInt, CD <: MInt, T](value: T)(implicit num: Numeric[T]) {
    import Quantity._

    def asInt = Quantity[M, KG, S, A, K, Mol, CD, Int](num.toType[Int](value))
    def asLong = Quantity[M, KG, S, A, K, Mol, CD, Long](num.toType[Long](value))
    def asFloat = Quantity[M, KG, S, A, K, Mol, CD, Float](num.toType[Float](value))
    def asDouble = Quantity[M, KG, S, A, K, Mol, CD, Double](num.toType[Double](value))

    def toInt: Int = (num.toType[Int](value))
    def toLong: Long = (num.toType[Long](value))
    def toFloat: Float = (num.toType[Float](value))
    def toDouble: Double = (num.toType[Double](value))

    type This = Quantity[M, KG, S, A, K, Mol, CD, T]

    def +(m: This) = Quantity[M, KG, S, A, K, Mol, CD, T](num.plus(value, m.value))
    def -(m: This) = Quantity[M, KG, S, A, K, Mol, CD, T](num.minus(value, m.value))
    def *[M2 <: MInt, KG2 <: MInt, S2 <: MInt, A2 <: MInt, K2 <: MInt, Mol2 <: MInt, CD2 <: MInt](m: Quantity[M2, KG2, S2, A2, K2, Mol2, CD2, T]) = Quantity[M + M2, KG + KG2, S + S2, A + A2, K + K2, Mol + Mol2, CD + CD2, T](num.times(value, m.value))
    def /[M2 <: MInt, KG2 <: MInt, S2 <: MInt, A2 <: MInt, K2 <: MInt, Mol2 <: MInt, CD2 <: MInt](m: Quantity[M2, KG2, S2, A2, K2, Mol2, CD2, T]) = Quantity[M - M2, KG - KG2, S - S2, A - A2, K - K2, Mol - Mol2, CD - CD2, T](num.div(value, m.value))

    def unitString(implicit u: ToString[M, KG, S, A, K, Mol, CD]) = value + u.toString
  }

  object Quantity {
    class ToString[M <: MInt, KG <: MInt, S <: MInt, A <: MInt, K <: MInt, Mol <: MInt, CD <: MInt](override val toString: String)
    implicit val length =            new ToString[_1, _0, _0, _0, _0, _0, _0](" m")
    implicit val mass =              new ToString[_0, _1, _0, _0, _0, _0, _0](" kg")
    implicit val time =              new ToString[_0, _0, _1, _0, _0, _0, _0](" s")
    implicit val currency =          new ToString[_0, _0, _0, _1, _0, _0, _0](" a")
    implicit val temperature =       new ToString[_0, _0, _0, _0, _1, _0, _0](" k")
    implicit val mol =               new ToString[_0, _0, _0, _0, _0, _1, _0](" mol")
    implicit val luminousIntensity = new ToString[_0, _0, _0, _0, _0, _1, _0](" cd")

    implicit val area =              new ToString[_2, _0, _0, _0, _0, _0, _0](" m^2")
    implicit val volume =            new ToString[_3, _0, _0, _0, _0, _0, _0](" m^3")
    implicit val speed =             new ToString[_1, _0, _1#Neg, _0, _0, _0, _0](" m/s")
    implicit val acceleration =      new ToString[_1, _0, _2#Neg, _0, _0, _0, _0](" m/s^2")
  }


  implicit def measure[T: Numeric](v: T): Quantity[_0, _0, _0, _0, _0, _0, _0, T] = Quantity[_0, _0, _0, _0, _0, _0, _0, T](v)

  implicit class QuantityConstructor[T: Numeric](v: T) {
    private val num = implicitly[Numeric[T]]

    def m   = Quantity[_1, _0, _0, _0, _0, _0, _0, T](v)
    def kg  = Quantity[_0, _1, _0, _0, _0, _0, _0, T](v)
    def s   = Quantity[_0, _0, _1, _0, _0, _0, _0, T](v)
    def a   = Quantity[_0, _0, _0, _1, _0, _0, _0, T](v)
    def k   = Quantity[_0, _0, _0, _0, _1, _0, _0, T](v)
    def mol = Quantity[_0, _0, _0, _0, _0, _1, _0, T](v)
    def cd  = Quantity[_0, _0, _0, _0, _0, _0, _1, T](v)

    def minute = Quantity[_0, _0, _1, _0, _0, _0, _0, T](num.times(v, num.fromInt(60)))
    def hour   = Quantity[_0, _0, _1, _0, _0, _0, _0, T](num.times(v, num.fromInt(3600)))
  }


  type Length[T]            = Quantity[_1, _0, _0, _0, _0, _0, _0, T]
  type Mass[T]              = Quantity[_0, _1, _0, _0, _0, _0, _0, T]
  type Duration[T]          = Quantity[_0, _0, _1, _0, _0, _0, _0, T]
  type Currency[T]          = Quantity[_0, _0, _0, _1, _0, _0, _0, T]
  type Temperature[T]       = Quantity[_0, _0, _0, _0, _1, _0, _0, T]
  type Mol[T]               = Quantity[_0, _0, _0, _0, _0, _1, _0, T]
  type LuminousIntensity[T] = Quantity[_0, _0, _0, _0, _0, _0, _1, T]

  type Area[T]         = Quantity[_2, _0, _0, _0, _0, _0, _0, T]
  type Volume[T]       = Quantity[_3, _0, _0, _0, _0, _0, _0, T]
  type Speed[T]        = Quantity[_1, _0, _1#Neg, _0, _0, _0, _0, T]
  type Acceleration[T] = Quantity[_1, _0, _2#Neg, _0, _0, _0, _0, T]
}
