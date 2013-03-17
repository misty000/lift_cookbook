package code.snippet

import net.liftweb.util._
import Helpers._

import net.liftweb.http._
import net.liftweb.http.js._
import JsCmds._

import scala.xml._

class JsonForm {

	def render =
		"#formToJson" #> ((ns: NodeSeq) => SHtml.jsonForm(jsonHandler, ns)) &
			"#jsonFormScript" #> Script(jsonHandler.jsCmd)

	object jsonHandler extends JsonHandler {

		def apply(in: Any): JsCmd = in match {
			case JsonCmd("processForm", target, params: Map[String, _], all) =>
				val name = params.getOrElse("name", "No Name")
				val motto = params.getOrElse("motto", "No Motto")
				SetHtml("result", Text(s"The motto of [$name] is [$motto]"))
			case _ =>
				SetHtml("result", Text("Unknown command"))
		}

	}

}