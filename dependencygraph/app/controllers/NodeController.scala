package controllers

import graph.DataExtraction
import play.api.mvc._
import javax.inject._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class NodeController @Inject()(cc : ControllerComponents) extends AbstractController(cc) {

	DataExtraction.allNodes = DataExtraction.processJSON("public/resources/data/data.json")

	/*val nodeNameArray : Array[String] = DataExtraction.allNodes.map(node => node._processName).toArray[String]
	val nodeDependenciesArray : Array[Array[String]] = DataExtraction.allNodes.map(node => node._dependencies.toArray[String]).toArray[Array[String]]
	val nodeOutputsArray : Array[Array[String]] = DataExtraction.allNodes.map(node => node._outputs.toArray[String]).toArray[Array[String]]*/

	def node() = Action { implicit request =>
	    DataExtraction.allNodes.foreach(parentNode => DataExtraction.allNodes(parentNode._1)._id)
		Ok(views.html.node(DataExtraction.allNodes))
	}

/*	def checkNode(id : String) = Action {
		val trueId = id.toInt
		Ok(views.html.checkNode(nodeNameArray(trueId), nodeDependenciesArray(trueId), nodeOutputsArray(trueId)))

		/*val parentNode : ParentNode = new ParentNode(DataExtraction.allNodes(trueId))
		val parentDependencies : Array[String] = parentNode._dependencyNodes.map(node => node._name).toArray[String]
		Ok(views.html.checkNode(parentNode._name, parentDependencies, parentNode._outputs.toArray[String]))*/
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

		DataExtraction.allNodes.foreach(node => {
			newNodeNameArray.foreach(newName => {
				//Append the dependencies and outputs to the associated parent node
				if (node._processName == newName && newName != null && newName != "") {
					newDependenciesArray.append(nodeDependenciesArray(node._id))
					newOutputsArray.append(nodeOutputsArray(node._id))
				}
			})
		})

		val transformedDependenciesArray = newDependenciesArray.toArray[Array[String]]
		val transformedOutputsArray = newOutputsArray.toArray[Array[String]]

		Ok(views.html.node(newNodeNameArray.toArray[String], transformedDependenciesArray, transformedOutputsArray))
	}*/
}