package graph

import play.api.libs.json.JsValue

class Node(name : JsValue, dependencies : Option[Seq[JsValue]] = None, outputs : Option[Seq[JsValue]] = None) {
	val _name : JsValue = name
	val _dependencies : Seq[JsValue] = dependencies.get
	val _outputs : Seq[JsValue] = outputs.get

}
