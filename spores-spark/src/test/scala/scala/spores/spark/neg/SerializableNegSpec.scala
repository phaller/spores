package scala.spores.spark.neg

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import scala.spores.spark.TestUtil._
import scala.spores.util.Feedback

@RunWith(classOf[JUnit4])
class SerializableNegSpec {
  @Test
  def `A class is not serializable`() {
    expectError(Feedback.NonSerializableType("Foo", "value foo")) {
      """
        import scala.spores._
        class Foo(val number: Int)
        val foo = new Foo(1)
        spore {
          () => capture(foo)
        }
      """
    }
  }

  @Test
  def `A trait is not serializable`() {
    expectError(Feedback.NonSerializableType("Foo", "value foo")) {
      """
        import scala.spores._
        trait Foo { val number: Int }
        val foo = new Foo { val number = 1 }
        spore {
          () => capture(foo)
        }
      """
    }
  }

  @Test
  def `An abstract class is not serializable`() {
    expectError(Feedback.NonSerializableType("Foo", "value foo")) {
      """
        import scala.spores._
        abstract class Foo(val number: Int)
        val foo = new Foo { val number = 1 }
        spore {
          () => capture(foo)
        }
      """
    }
  }

  @Test
  def `An object is not serializable`() {
    expectError(Feedback.NonSerializableType("Foo.type", "value foo")) {
      """
        import scala.spores._
        object Foo { val number = 1 }
        val foo = Foo
        spore {
          () => capture(foo)
        }
      """
    }
  }
}