package code.snippet

import net.liftweb.common.Empty
import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.http.js.JsCmd
import xml.Text
import net.liftweb.util.Helpers._
import net.liftweb.http.{S, SHtml}
import util.Random

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午4:49
 */
class CallServerWhenSelectOptionChanges {
	type Planet = String
	type LightYears = Double

	val database = Map[Planet, LightYears](
		"Alpha Centauri Bb" -> 4.23,
		"Tau Ceti e" -> 11.90,
		"Tau Ceti f" -> 11.90,
		"Gliese 876 d" -> 15.00,
		"82 G Eridani b" -> 19.71
	)

	def render = {
		val blankOption = ("" -> "")
		val options: List[(String, String)] = blankOption ::
			database.keys.map(p => (p, p)).toList
		val default = Empty
		def handler(selected: String): JsCmd = {
			val selVal: Double = database.getOrElse(selected, 0)

			val opts = 1 to (Random.nextInt(5) + 2) map (i => (i.toString, i.toString))
			val node = SHtml.select(opts, default, s => ())
			SetHtml("distance", Text(selVal + " light years")) &
				SetHtml("price", node)
		}
		var selectedValue: String = ""
		"#dropdown" #> SHtml.onEvents("onChange")(handler) {
			println("onEvents")
			SHtml.select(options, default, selectedValue = _)
		} &
			"type=submit" #> SHtml.onSubmitUnit(() => {
				println("onSubmitUnit")
				S.notice("Destination " + selectedValue)
			})
	}
}
