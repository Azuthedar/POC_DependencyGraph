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

	val nodeNameArray : Array[String] = nodes.map(node => node._name).toArray[String]
	val nodeDependenciesArray : Array[Array[String]] = nodes.map(node => node._dependencies.toArray[String]).toArray[Array[String]]
	val nodeOutputsArray : Array[Array[String]] = nodes.map(node => node._outputs.toArray[String]).toArray[Array[String]]

	def node() = Action { implicit request =>
		Ok(views.html.node(nodeNameArray, nodeDependenciesArray, nodeOutputsArray))
	}

	def checkNode(id : String) = Action {
		val trueId = id.toInt
		Ok(views.html.checkNode(nodeNameArray(trueId), nodeDependenciesArray(trueId), nodeOutputsArray(trueId)))
	}

	def filterNodes(seq : String) = Action {implicit request =>
		val nNArr : Array[String] = nodeNameArray.map(node => if (node.contains(seq)) node else null)

		val newNodeNameArray : ArrayBuffer[String] = new ArrayBuffer()
		val newDependenciesArray : ArrayBuffer[Array[String]] = new ArrayBuffer()
		val newOutputsArray : ArrayBuffer[Array[String]] = new ArrayBuffer()

		//Loop through all the found items and remove anything that is empty or null
		nNArr.foreach(name => {
			if (name != "" && name != null) {
				newNodeNameArray.append(name)
			}
		})

		nodes.foreach(node => {
			newNodeNameArray.foreach(newName => {
				//Append the dependencies and outputs to the associated parent node
				if (node._name == newName && newName != null && newName != "") {
					newDependenciesArray.append(nodeDependenciesArray(node._id))
					newOutputsArray.append(nodeOutputsArray(node._id))
				}
			})
		})

		val transformedDependenciesArray = newDependenciesArray.toArray[Array[String]]
		val transformedOutputsArray = newOutputsArray.toArray[Array[String]]

		Ok(views.html.node(newNodeNameArray.toArray[String], transformedDependenciesArray, transformedOutputsArray))
	}
}