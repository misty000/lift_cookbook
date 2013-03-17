package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.LiftRules
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午3:21
 */
object ReturningJSON extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(ReturningJSON)
	}

	serve {
		case JsonGet("quotation" :: Nil, req) => {
			("text" -> "A beach house isn't just real estate. It's a state of mind.") ~
				("by" -> "Douglas Adams"): JValue
		}
	}
}
