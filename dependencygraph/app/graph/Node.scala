package graph

import scala.collection.mutable.ArrayBuffer

class Node(name : String)
{
	val _id : String = name
	val _sources : ArrayBuffer[Node] = new ArrayBuffer()
	val _outputs : ArrayBuffer[Node] = new ArrayBuffer()
	val _dependencies : ArrayBuffer[Node] = new ArrayBuffer()

	def addSource(node : Node) : Unit = {
		this._sources += node
	}

	def addOutput(node : Node) : Unit = {
		this._outputs += node
	}

	def getSources : ArrayBuffer[Node] = {
		this._sources
	}

	def getOutputs : ArrayBuffer[Node] = {
		this._outputs
	}

	def dependantExistWithinNode(id : String) : Boolean = {
		this._outputs.foreach(output => {
			if (output._id == id) {
				return true
			}
		})
		false
	}

	def addDependency(node : Node): Unit = {
		if (!this._dependencies.contains(node))
			this._dependencies.append(node)
	}

	def hasDependencies : Boolean = {
		this._dependencies.nonEmpty
	}
}
