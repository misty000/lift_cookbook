package code.snippet

import org.joda.time.DateTime
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.http.S

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午3:44
 */
class SitemapContent {

	case class Post(url: String, date: DateTime)

	lazy val entries = Post("/welcome", new DateTime) ::
		Post("/about", new DateTime) ::
		Nil

	val siteLastUdated = new DateTime

	def base: CssSel =
		"loc *" #> s"http://${S.hostName}" &
			"lastmod *" #> siteLastUdated.toString("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")

	def list: CssSel =
		"url *" #> entries.map(post => {
			"loc *" #> "http://%s%s".format(S.hostName, post.url) &
				"lastmod *" #> post.date.toString("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
		})
}
