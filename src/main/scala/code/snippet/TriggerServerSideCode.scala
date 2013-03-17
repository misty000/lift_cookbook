package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午4:34
 */
class TriggerServerSideCode {
	def easy = "#myButton [onclick]" #> SHtml.ajaxInvoke(() => {
		println("click")
		JsCmds.Alert("Hi")
	})
}
