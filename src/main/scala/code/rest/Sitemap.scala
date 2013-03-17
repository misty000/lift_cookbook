package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http._

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午4:11
 */
object Sitemap extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(Sitemap)
	}

	serve {
		case Req("sitemap" :: Nil, _, GetRequest) =>
			println("sitemap")
			XmlResponse(
				S.render(<lift:embed what="sitemap"/>, S.request.get.request).head
			)
	}
}
