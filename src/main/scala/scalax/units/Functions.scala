package scalax.units

import scala.language.higherKinds

object Functions {
  trait TFn1B {
    type In
    type Out
    type Apply[T <: In] <: Out
  }

  trait TFn1[I, O] extends TFn1B {
    type In >: I
    type Out <: O
  }

  type ->[Arg, F <: TFn1[Arg, _]] = F#Apply[Arg]

  trait >>[F1 <: TFn1[_, _ <: F2#In], F2 <: TFn1[_, _]] extends TFn1[F1#In, F2#Out] {
    override type In = F1#In
    override type Out = F2#Out
    type Apply[T <: In] = F2#Apply[F1#Apply[T]]
  }
}
