package com.tinkerpop.gremlin.scala

import com.tinkerpop.pipes.branch.util.LoopBundle

/**Provides convenience methods for LoopBundle[T]. */
class ScalaLoopBundle[T](val bundle: LoopBundle[T]) {
  def step = bundle.getStep

  def obj = bundle.getObject
}

object ScalaLoopBundle {
  implicit def wrap[T](bundle: LoopBundle[T]) = new ScalaLoopBundle[T](bundle)

  implicit def unwrap[T](wrapper: ScalaLoopBundle[T]) = wrapper.bundle
}