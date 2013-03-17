package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.util.Helpers._
import net.liftweb.common.Box
import net.liftweb.http.{LiftRules, RedirectResponse, StreamingResponse, LiftResponse}
import java.io.{FileInputStream, File}

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-17
 * Time: 上午10:00
 */
object ServingFileWithAccessControl extends RestHelper {
	def init() {
		LiftRules.dispatch.append(ServingFileWithAccessControl)
	}

	serve {
		//		case Get("download" :: Known(fileId) :: Nil, req) => {
		//			if (permitted) fileResponse(fileId)
		//			else Full(RedirectResponse("/sorry"))
		//		}
		case Get("download" :: Known(fileId) :: Nil, _) if permitted => fileResponse(fileId)
		case Get("download" :: _, _) => RedirectResponse("/sorry")
	}

	private def permitted = math.random < 0.5d

	private def fileResponse(fileId: String): Box[LiftResponse] = {
		for {
			file <- Box !! new File("/tmp" + fileId)
			input <- tryo(new FileInputStream(file))
		} yield {
			StreamingResponse(
				data = input,
				onEnd = () => input.close(),
				size = file.length,
				headers = Nil,
				cookies = Nil,
				code = 200
			)
		}
	}

	val knownFiles = List("important")

	object Known {
		def unapply(fileId: String): Option[String] = knownFiles.find(_ == fileId)
	}


}
