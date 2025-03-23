package controllers

import tools.Constants._

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import services.TitleService

@Singleton
class TitleController @Inject()(val controllerComponents: ControllerComponents, titleService: TitleService) extends BaseController {

  def getTitles() = Action.async(parse.json) { request =>
    val body = (request.body \ URLS).asOpt[Seq[String]]
    body match {
      case Some(urls) if urls.nonEmpty =>
        val titlesFuture = Future.sequence(urls.map(url => titleService.getTitle(url)))

        titlesFuture.map { titles =>
          Ok(Json.toJson(urls.zip(titles).map { case (url, title) => Json.obj(URL_FIELD -> url, TITLE_FIELD -> title) }))
        }

      case Some(urls) if urls.isEmpty =>
        Future.successful(Ok(Json.toJson(Seq.empty[JsValue])))

      case _ =>
        val errorResponse = Json.obj(MESSAGE_FIELD -> INCORRECT_FORMAT_ERROR_MESSAGE)
        Future.successful(BadRequest(errorResponse))
    }
  }
}
