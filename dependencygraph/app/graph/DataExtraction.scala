package graph

import java.io.FileInputStream

import play.api.libs.json._

import scala.collection.mutable.ArrayBuffer

object DataExtraction {
	def processJSON(filePath : String) : ArrayBuffer[Node] =
	{
		if (filePath.contains(".json")) {
			var nodeArr : ArrayBuffer[Node] = new ArrayBuffer()
			val jsonFile = new FileInputStream(filePath)
			val jsonParsed = Json.parse(jsonFile)

			val fields = parseLineInfo(jsonParsed)

			for (i <- 1 to fields._1.length - 1)
			{
				val newNode : Node = new Node(fields._1(i), fields._2(i).asOpt[Seq[JsValue]], fields._3(i).asOpt[Seq[JsValue]])
				nodeArr += newNode
			}
			nodeArr
		}
		else {
			null
		}
	}

	private def parseLineInfo(jsonParsed : JsValue) : (Seq[JsValue], Seq[JsValue], Seq[JsValue]) = {
		val names = jsonParsed \ "subProcesses" \\ "name"
		val dependencies = jsonParsed \ "subProcesses" \\ "sources"
		val outputs = jsonParsed \ "subProcesses" \\ "outputs"

		(names, dependencies, outputs)
	}
}