package code.lib

import net.liftweb.http.Req


/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午4:32
 */
object RobotDetector {
	val botNames = "Googlebot" ::
		"Mediapartners-Google" ::
		"AdsBot-Google" :: Nil

	def known_?(ua: String) = botNames.exists(ua contains _)

	def googlebot_?(r: Req): Boolean = r.header("User-Agent").exists(known_?)
}
