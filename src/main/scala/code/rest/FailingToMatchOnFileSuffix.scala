package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.{GetRequest, Req, LiftRules}
import xml.Text

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午2:44
 */
object FailingToMatchOnFileSuffix extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(FailingToMatchOnFileSuffix)
	}

	serve {
		case Req("reports" :: name :: Nil, "csv", GetRequest) => {
			Text(s"Here's your CSV report for $name")
		}
		case Req("reports" :: name :: Nil, "", GetRequest) => {
			Text(s"Here's your CSV report for $name")
		}
	}
}
