package code.lib

import net.liftweb.common.Box
import net.liftweb.http.{Templates, S, LiftResponse}

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午2:10
 */
object CustomStatus {
	def my403: Box[LiftResponse] = for {
		session <- S.session
		req <- S.request
		template = Templates("403" :: Nil)
		response <- session.processTemplate(template, req, req.path, 403)
	} yield response
}
