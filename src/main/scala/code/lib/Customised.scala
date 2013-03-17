package code.lib

import net.liftweb.http.{Templates, S, LiftResponse}
import net.liftweb.common.Box

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午2:39
 */
object Customised {
	val definedPages = 403 :: 500 :: Nil

	def unapply(resp: LiftResponse): Option[LiftResponse] =
		definedPages.find(_ == resp.toResponse.code).flatMap(toResponse)

	def toResponse(status: Int): Box[LiftResponse] =
		for {
			session <- S.session
			req <- S.request
			template = Templates(status.toString :: Nil)
			response <- session.processTemplate(template, req, req.path, status)
		} yield response
}
