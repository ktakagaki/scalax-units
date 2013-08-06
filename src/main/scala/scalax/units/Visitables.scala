package scalax.units

import scala.language.higherKinds

object Visitables {
  trait TypeVisitor {
    type ResultType
  }

  trait Visitable[V <: TypeVisitor] {
    type Accept[V2 <: V] <: V2#ResultType
  }
}
