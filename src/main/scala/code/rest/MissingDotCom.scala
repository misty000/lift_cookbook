package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.LiftRules
import xml.Text

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午2:20
 */
object MissingDotCom extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(MissingDotCom)
	}

	serve {
		case Get("email" :: e :: "send" :: Nil, req) => {
			Text(s"In middle: $e")
		}
		case Get("email" :: e :: Nil, req) => {
			Text(s"At end: $e")
		}
	}
}
