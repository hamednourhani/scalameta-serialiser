package scala.meta.serialiser

import org.scalatest._

object TestEntities {
  // @mappable case class SimpleCaseClass(i: Int, s: String)
  // @mappable case class WithTypeParam[N <: Number](n: Number)
  // @mappable case class WithBody(i: Int) { def banana: Int = i }

  object CaseClassWithCompanion { def existingFun(): Int = 42 }
  @mappable case class CaseClassWithCompanion (i: Int, s: String)
}

class MappableTest extends WordSpec with Matchers {
  import TestEntities._

  // "simple case class" should {
  //   "serialise and deserialise" in {
  //     val testInstance = SimpleCaseClass(i = 42, s = "something")
  //     val keyValues = SimpleCaseClass.toMap(testInstance)
  //     SimpleCaseClass.fromMap(keyValues) shouldBe Some(testInstance)
  //   }
  // }

  // "case class with type param" should {
  //   "serialise and deserialise" in {
  //     val testInstance = WithTypeParam[Integer](n = 43)
  //     val keyValues = WithTypeParam.toMap(testInstance)
  //     WithTypeParam.fromMap(keyValues) shouldBe Some(testInstance)
  //   }
  // }

  // "case class with body" should {
  //   "still have the body as before" in {
  //     WithBody(100).banana shouldBe 100
  //   }
  // }

  "case class with companion" should {
    "serialise and deserialise" in {
      val testInstance = CaseClassWithCompanion(i = 42, s = "something")
      val keyValues = CaseClassWithCompanion.toMap(testInstance)
      CaseClassWithCompanion.fromMap(keyValues) shouldBe Some(testInstance)
    }

    // "maintain existing code in companion" in {
    //   CaseClassWithCompanion.existingFun shouldBe 42
    // }
  }

  // "fromMap" should {
  //   "return None if provided with invalid data" in {
  //     val invalidKeyValues = Map("in" -> "valid")

  //     Seq(SimpleCaseClass.fromMap _, WithTypeParam.fromMap _, WithBody.fromMap _) foreach { fromMap =>
  //       fromMap(invalidKeyValues) shouldBe None
  //     }
  //   }
  // }

}
