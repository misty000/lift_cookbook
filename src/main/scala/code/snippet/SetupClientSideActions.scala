package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.{RedirectTo, Alert}
import net.liftweb.http.js.JE._

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午8:54
 */
class SetupClientSideActions {
	def buttonBind = "#button [onclick]" #> "$('#button').fadeOut('slow')" &
		"#button2 [onclick]" #> (Alert("Here we go...") & RedirectTo("http://liftweb.net")).toJsCmd &
		"#button3 [onclick]" #> new Call("greet", "you").toJsCmd
}
