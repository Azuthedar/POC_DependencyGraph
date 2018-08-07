/*
package graph

import scala.collection.mutable.ArrayBuffer

class ParentNode(parentNode : Node) {
	val _processName : String = parentNode._processName
	val _dependencyNodes : ArrayBuffer[Node] = new ArrayBuffer()
	val _outputs : ArrayBuffer[String] = parentNode._outputs

	this._dependencyNodes.append(getNodeDependencies(parentNode._dependencies))
	this._dependencyNodes.remove(this._dependencyNodes.length - 1)


	 def getNodeDependencies(dependencyOfNode : ArrayBuffer[String]) : Node = {
		// LOGIC: If the node is a dependency to the parent node append all the dependency nodes until there are no dependencies left
		DataExtraction.allNodes.foreach(node => {
			dependencyOfNode.foreach(depName => {
				//this._dependencyNodes.append(new Node(depName + " " + node._name))
				if (node._processName.contains(depName))
				{
					if (!this._dependencyNodes.contains(node))
						this._dependencyNodes.append(node)
					if (node._dependencies != null && node._dependencies.nonEmpty)
						this._dependencyNodes.append(getNodeDependencies(node._dependencies))
				}
			})
		})
		null
	}
}*/
