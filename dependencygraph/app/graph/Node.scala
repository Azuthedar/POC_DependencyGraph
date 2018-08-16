package graph

import play.api.libs.json.{JsValue, Json, Writes}

import scala.collection.mutable.ArrayBuffer

class Node(name : String) {
	val _id: String = name
	val _sources: ArrayBuffer[Node] = new ArrayBuffer()
	val _outputs: ArrayBuffer[Node] = new ArrayBuffer()
	val _dependencies: ArrayBuffer[(Node, Node)] = new ArrayBuffer()
	var depth = 0

	def addSource(node: Node): Unit = {
		this._sources += node
	}

	def addOutput(node: Node): Unit = {
		this._outputs += node
	}

	def getSources: ArrayBuffer[Node] = {
		this._sources
	}

	def getOutputs: ArrayBuffer[Node] = {
		this._outputs
	}

	def dependantExistWithinNode(id: String): Boolean = {
		this._outputs.foreach(output => {
			if (output._id == id) {
				return true
			}
		})
		false
	}

	def addDependency(parentNode: Node, actualNode: Node): Unit = {
		if (!this._dependencies.contains((parentNode, actualNode)))
			this._dependencies.append((parentNode, actualNode))
	}

	def hasDependencies: Boolean = {
		this._dependencies.nonEmpty
	}
}

object Node {
	var p_depArray : ArrayBuffer[(Node, Node)] = new ArrayBuffer()

	implicit val implicitNodeWrites = new Writes[Node] {
		def writes(node : Node) : JsValue = {
			val nodeJson = Json.obj(
				"id" -> node._id.toString,
				"sources" -> node._sources.map(source => source._id),
				"outputs" -> node._outputs.map(output => output._id),
				"dependencies" -> node._dependencies.map(dependency => dependency._2._id)
			)
			println(nodeJson + "\n\n\n")
			nodeJson
		}
	}
}
