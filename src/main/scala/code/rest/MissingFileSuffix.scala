package code.rest

import net.liftweb.http.rest.RestHelper
import xml.Text
import net.liftweb.http.LiftRules

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 下午12:31
 */
object MissingFileSuffix extends RestHelper {
	private def reunite(name: String, suffix: String) =
		if (suffix.isEmpty) name else name + "." + suffix

	serve {
		case Get("download" :: file :: Nil, req) if req.path.suffix == "png" => {
			Text(s"You requested PNG file called $file")
		}
		case Get("download" :: file :: Nil, req) => {
			Text("You requested " + reunite(file, req.path.suffix))
		}
		case Get("negotiate" :: file :: Nil, req) => {
			val toSend =
				if (req.header("Accept").exists(_ == "image/webp")) file + ".webp"
				else file + ".png"
			Text(s"You requested $file, would send $toSend")
		}
	}

	def init() {
		LiftRules.statelessDispatch.append(MissingFileSuffix)
	}
}
