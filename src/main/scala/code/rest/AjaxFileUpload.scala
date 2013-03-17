package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.{BadResponse, ResponseWithReason, OkResponse}

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 上午10:44
 */
object AjaxFileUpload extends RestHelper {
	serve {
		case "upload" :: Nil Post req =>
			if (req.uploadedFiles.exists(_.mimeType != "image/png"))
				ResponseWithReason(BadResponse(), "Only PNGs")
			else {
				for (file <- req.uploadedFiles) {
					println(s"Received: ${file.fileName}")
				}
				OkResponse()
			}
		// case Post("upload" :: Nil, req) =>
	}
}
