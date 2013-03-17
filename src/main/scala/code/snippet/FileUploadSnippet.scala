package code.snippet

import net.liftweb.http.{SHtml, FileParamHolder}
import net.liftweb.common.{Full, Box, Empty}
import net.liftweb.util.Helpers._

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-16
 * Time: 上午9:59
 */
class FileUploadSnippet {
	def render = {
		var upload: Box[FileParamHolder] = Empty
		def processForm() = upload match {
			case Full(FileParamHolder(_, mimeType, fileName, file)) => {
				println(s"$fileName of type $mimeType is ${file.length} bytes long")
			}
			case _ => println("No file?")
		}
		"#file" #> SHtml.fileUpload(f => upload = Full(f)) &
			"type=submit" #> SHtml.onSubmitUnit(processForm)
	}
}
