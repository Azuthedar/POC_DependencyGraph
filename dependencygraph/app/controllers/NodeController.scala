package controllers

import graph.{DataExtraction, Node}
import play.api.mvc._
import javax.inject._
import play.filters.headers.SecurityHeadersFilter

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class NodeController @Inject()(cc : ControllerComponents) extends AbstractController(cc) {

	DataExtraction.allNodes = DataExtraction.processJSON("public/resources/data/data.json")
	DataExtraction.getAllNodeDependencies()

	def node() = Action { implicit request =>
		Ok(views.html.node(DataExtraction.allNodes))
	}
	def nodeDetails(id : String) = Action {
		Ok(views.html.nodeDetails(DataExtraction.allNodes(id)))
	}

	def filterNodes(seq : String) = Action { implicit request =>
		Ok(views.html.node(DataExtraction.allNodes.filter(node => node._2._id.contains(seq))))
	}
}