package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.LiftRules

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午12:18
 */
object DryURLs extends RestHelper {
	def init() {
		LiftRules.statelessDispatch.append(DryURLs)
	}

	serve("issues" / "by-state" prefix {
		case "open" :: Nil XmlGet _ => <p>None open</p>
		case "closed" :: Nil XmlGet _ => <p>None closed</p>
		case "closed" :: Nil XmlDelete _ => <p>All deleted</p>
	})
}
