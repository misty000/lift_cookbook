package code.lib

import org.specs2.mutable.Specification
import net.liftweb.mocks.MockHttpServletRequest
import net.liftweb.mockweb.MockWeb


/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午4:38
 */
class SingleRobotDetectorSpec extends Specification {
	"Google Bot Detector" should {
		"spot a web crawler" in {
			val userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1)"
			val http = new MockHttpServletRequest
			http.headers = Map("User-Agent" -> List(userAgent))

			MockWeb.testReq(http) {
				r => RobotDetector.googlebot_?(r) must beTrue
			}
		}
	}
}
