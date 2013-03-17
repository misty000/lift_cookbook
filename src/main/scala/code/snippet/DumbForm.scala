package code.snippet

import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.util.Helpers._
import net.liftweb.util.PassThru

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午5:24
 */
class DumbForm {
	val inputParam = for {
		r <- S.request if r.post_?
		v <- S.param("it")
	} yield v

	def render = inputParam match {
		case Full(x) =>
			println("Input is: " + x)
			"#result *" #> x
		case _ =>
			println("No input present! Rendering input form HTML")
			PassThru
	}
}
