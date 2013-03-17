package code.lib

import net.liftweb.util.{NamedPF, Helpers}
import net.liftweb.http.{RewriteResponse, ParsePath, RewriteRequest, LiftRules}

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 上午11:46
 */
object CustomResourceId {
	def init() {
		val resourceId = Helpers.nextFuncName
		LiftRules.attachResourceId = (path: String) => {
			"/cache/" + resourceId + path
		}

		LiftRules.statelessRewrite.prepend(NamedPF("BrowserCacheAssist") {
			case RewriteRequest(ParsePath("cache" :: id :: file, suffix, _, _), _, _) =>
				RewriteResponse(file, suffix)
		})
	}
}
