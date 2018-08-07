package graph

import java.io.FileInputStream

import play.api.libs.json._
import scala.collection.mutable.Map

import scala.collection.mutable.ArrayBuffer

object DataExtraction {

	var allNodes: Map[String, Node] = Map[String, Node]()

	def processJSON(filePath: String): Map[String, Node] = {
		if (filePath.contains(".json")) {
			val jsonFile = new FileInputStream(filePath)
			val jsonParsed = Json.parse(jsonFile)

			val fields = parseLineInfo(jsonParsed)

			val processNodeMap: Map[String, Node] = Map[String, Node]()

			val tempNodeMap: Map[String, Node] = Map[String, Node]()

			for (i <- 0 to fields._1.length - 1) {
				val processName: String = fields._1(i).toString()

				val sources: ArrayBuffer[String] = fields._2(i).asOpt[ArrayBuffer[String]].orNull
				val outputs: ArrayBuffer[String] = fields._3(i).asOpt[ArrayBuffer[String]].orNull

				val newProcessNode: Node = new Node(processName)

				//Add in given sources
				for (sourceName <- sources) {
					val sourceNode: Node = if (!tempNodeMap.contains(sourceName)) {
						val newSourceNode = new Node(sourceName)
						tempNodeMap += (sourceName -> newSourceNode)
						newSourceNode
					}
					else {
						tempNodeMap(sourceName)
					}

					newProcessNode.addSource(sourceNode)
				}

				//Add in given outputs
				for (outputName <- outputs) {
					val outputNode: Node = if (!tempNodeMap.contains(outputName)) {
						val newOutputMap = new Node(outputName)
						tempNodeMap += (outputName -> newOutputMap)
						newOutputMap
					}
					else {
						tempNodeMap(outputName)
					}
					newProcessNode.addOutput(outputNode)
				}

				processNodeMap += (processName -> newProcessNode)
			}
			processNodeMap
		}
		else {
			null
		}
	}

	private def parseLineInfo(jsonParsed: JsValue): (Seq[JsValue], Seq[JsValue], Seq[JsValue]) = {
		val names = jsonParsed \ "subProcesses" \\ "name"
		val dependencies = jsonParsed \ "subProcesses" \\ "sources"
		val outputs = jsonParsed \ "subProcesses" \\ "outputs"

		(names, dependencies, outputs)
	}

	def getAllNodeDependencies(): Unit = {
		//Need to loop through all the nodes twice to look at their relative dependencies, one as the parent and the other as the target
		this.allNodes.foreach(parentNode => {
			this.allNodes.foreach(otherNode => {
				//Check each of the parent sources and see if the source's file exists in another node, if it does add it as a dependency
				parentNode._2._sources.foreach(source => {
					if (otherNode._2.dependantExistWithinNode(source._id)) {
						parentNode._2.addDependency(otherNode._2)
					}
				})
			})
		})
	}
}