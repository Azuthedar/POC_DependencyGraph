package controllers

import graph.DataExtraction
import graph.Node
import play.api.mvc._
import play.api._
import javax.inject._
import play.api.http.Writeable
import play.api.libs.json.{JsValue, Json, Writes}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class NodeController @Inject()(cc : ControllerComponents) extends AbstractController(cc) {

	val nodes : ArrayBuffer[Node] = DataExtraction.processJSON("public/resources/data/data.json")

	def node = Action { implicit request =>
		Ok("Names: " + nodes.map(node => node._name + "\n"))
	}

	def detailedInformation = Action { implicit request =>
		Ok("Detailed Information\n" + nodes.map(node => "Name: " + node._name + " Dependencies: " + node._dependencies + " Outputs: " + node._outputs + "\n"))
	}
}
