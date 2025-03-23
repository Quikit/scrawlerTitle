package services

import org.jsoup.Jsoup
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TitleService {

  def getTitle(url: String): Future[String] = Future {
    try {
      val doc = Jsoup.connect(url).get()
      doc.title()
    } catch {
      case _: Exception => "Title not found"
    }
  }
}
