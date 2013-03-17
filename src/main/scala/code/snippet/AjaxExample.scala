package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js._
import JsCmds._

import scala.xml.Text

class AjaxExample {

	var inputVal = "default"

	def process(): JsCmd = {
		println("Received: " + inputVal)
		SetHtml("result", Text(inputVal))
	}

	def render = {
		"name=info" #> (
			SHtml.text(inputVal, inputVal = _) ++
				SHtml.hidden(process))
	}

}
