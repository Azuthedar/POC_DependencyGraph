package graph

import scala.collection.mutable.ArrayBuffer

class Node(name : String, dependencies : Option[ArrayBuffer[String]] = None, outputs : Option[ArrayBuffer[String]] = None)
{
	val _id : Int = Node.id
	Node.id += 1
	val _name : String= name
	val _dependencies : ArrayBuffer[String] = dependencies.orNull
	val _outputs : ArrayBuffer[String] = outputs.orNull
}

object Node {
	var id : Int = 0
}
