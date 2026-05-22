import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object ScalaAsyncInitRepro extends App {
  private def trigger(): Unit = {
    val f = Future(ScalaAsyncInitRepro.toString)
    println(Await.result(f, 1.second))
  }

  trigger()
}
