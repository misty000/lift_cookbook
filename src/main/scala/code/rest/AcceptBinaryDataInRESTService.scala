package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.{Req, LiftRules}

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午2:59
 */
object AcceptBinaryDataInRESTService extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(AcceptBinaryDataInRESTService)
	}

	serve {
		case Post("upload" :: Nil, req) => {
			for {
				bodyBytes <- req.body
			} yield
				<info>Received
					{bodyBytes.length}
					bytes
				</info>
		}
		case Post("jpg" :: Nil, JPeg(req)) => {
			for {
				bodyBytes <- req.body
			} yield
				<info>Jpeg Received
					{bodyBytes.length}
					bytes
				</info>
		}
	}

	object JPeg {
		def unapply(req: Req): Option[Req] =
			req.contentType.filter(_ == "image/jpg").map(_ => req)
	}

}
