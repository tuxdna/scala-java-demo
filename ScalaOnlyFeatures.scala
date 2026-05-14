object ScalaOnlyFeatures extends App {
  println("Scala-only feature highlights")

  // 1) Extension methods via implicit class (no Java language equivalent).
  implicit class StringOps(private val value: String) extends AnyVal {
    def shout: String = value.toUpperCase + "!"
  }

  // 2) for-comprehension over monads (Option) with concise syntax.
  val maybeUser: Option[String] = Some("ava")
  val maybeDomain: Option[String] = Some("example.com")
  val maybeEmail = for {
    user <- maybeUser
    domain <- maybeDomain
  } yield s"$user@$domain"

  // 3) Case class copy with named arguments.
  val original = Person(name = "Ava", age = 31, city = "Pune")
  val moved = original.copy(city = "Bengaluru")

  println(s"Extension method result: ${"scala".shout}")
  println(s"For-comprehension result: $maybeEmail")
  println(s"Case class copy result: $moved")
}

case class Person(name: String, age: Int, city: String)
