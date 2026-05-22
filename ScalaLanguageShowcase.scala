import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.Try

object ScalaLanguageShowcase {
  def main(args: Array[String]): Unit = {
    println("Scala Essentials: language feature showcase")
    // 1) val/var and concise type inference.
    variablesAndTypeInference()
    // 2) Case classes for compact domain modeling.
    caseClassAndCompanion()
    // 3) Option-based null safety.
    nullSafetyWithOption()
    // 4) Functional collection transformations.
    functionalCollections()
    // 5) Exhaustive pattern matching.
    patternMatching()
    // 6) Async operations with Future.
    asyncProgramming()
    // 7) Error modeling without checked exceptions.
    errorHandlingWithoutCheckedExceptions()
    // 8) Immutable collections by default.
    immutableCollectionsByDefault()
    // 9) Trait-based composition and mixins.
    traitsAndMixins()
  }

  private def variablesAndTypeInference(): Unit = {
    // val is immutable and preferred for most code.
    val immutable = "Scala favors immutability with val"
    // var is mutable and used when mutation is intentional.
    var mutableCounter = 0
    mutableCounter += 1
    val words = List("Java", "Scala", "Kotlin")
    println("\n1) Variables and type inference")
    println(s"Immutable text: $immutable")
    println(s"Mutable counter: $mutableCounter")
    println(s"Words: $words")
  }

  private def caseClassAndCompanion(): Unit = {
    // Case classes auto-generate equals/hashCode/toString/copy.
    val user = User("Ava", 31)
    println("\n2) OOP + FP model with case classes")
    println(s"User: $user")
  }

  private def nullSafetyWithOption(): Unit = {
    // Option represents present/absent values without null checks.
    val email: Option[String] = Some("ava@example.com")
    val normalized = email.map(_.toLowerCase).getOrElse("unknown@example.com")
    println("\n3) Null handling via Option")
    println(s"Normalized email: $normalized")
  }

  private def functionalCollections(): Unit = {
    val scores = List(7, 1, 5, 10, 9)
    // Collection API is FP-first, so pipelines are very concise.
    val transformed = scores.filter(_ >= 5).sorted(Ordering[Int].reverse).map(_ * 10)
    val lengths = List("java", "scala").map(w => w -> w.length).toMap
    println("\n4) Functional style with collections")
    println(s"Transformed scores: $transformed")
    println(s"Word lengths: $lengths")
  }

  private def patternMatching(): Unit = {
    val input: Any = 42
    // match is expression-based and can return a value directly.
    val description = input match {
      case number: Int if number > 0 => s"positive integer: $number"
      case text: String               => s"string of size ${text.length}"
      case _                          => "unknown"
    }
    println("\n5) Pattern matching")
    println(s"Description: $description")
  }

  private def asyncProgramming(): Unit = {
    // Future pipelines compose async stages declaratively.
    val greetingFuture = Future("hello").map(_ + " from Future")
    println("\n6) Async programming")
    println(s"Async result: ${Await.result(greetingFuture, 2.seconds)}")
  }

  private def errorHandlingWithoutCheckedExceptions(): Unit = {
    println("\n7) Error handling without checked exceptions")
    val ok = parsePositiveInt("123")
    val bad = parsePositiveInt("-7")
    println(s"Successful parse: $ok")
    println(s"Failed parse: $bad")
  }

  private def parsePositiveInt(input: String): Either[String, Int] =
    // Either models success/failure in the type system.
    Try(input.toInt).toEither.left.map(_.getMessage).flatMap { value =>
      if (value > 0) Right(value)
      else Left(s"Expected a positive number but got $value")
    }

  private def immutableCollectionsByDefault(): Unit = {
    // Most Scala collections are persistent and immutable by default.
    val immutable = List("A", "B")
    val updated = "C" :: immutable
    println("\n8) Immutable collections by default")
    println(s"Original immutable list: $immutable")
    println(s"Updated (new) immutable list: $updated")
  }

  private def traitsAndMixins(): Unit = {
    val greeter = new FriendlyGreeter
    println("\n9) Behavior abstraction with traits")
    println(greeter.greet("Java developer"))
  }
}

case class User(name: String, age: Int)

trait Greeter {
  def greet(name: String): String
}

trait EnthusiasticTone {
  def withTone(text: String): String = s"$text!"
}

class FriendlyGreeter extends Greeter with EnthusiasticTone {
  // Traits are mixed in to compose behavior without deep inheritance.
  override def greet(name: String): String = withTone(s"Hello, $name")
}
