import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ScalaAsyncInitSafe {
  def main(args: Array[String]): Unit = {
    val f = Future(ScalaAsyncInitSafe.toString)
    println(Await.result(f, 1.second))
  }
}
