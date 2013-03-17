package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http._
import xml.Text
import net.liftweb.http.InMemoryResponse
import ResponseShortcutException._

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午3:31
 */
class DownloadLink {
	val poem = "Roses are red," ::
		"Violets are blue" ::
		"Lift rocks!" ::
		"And so do you." :: Nil

	def render = ".poem" #> poem.map(line => ".line" #> line) &
		"#link1" #> downloadLink &
		"#link2" #> downloadLink2

	def downloadLink = SHtml.link(
		to = "/notused",
		func = () => throw new ResponseShortcutException(poemTextFile),
		body = Text("Download")
	)

	def downloadLink2 = SHtml.link(
		to = "/notused2",
		func = () => {
			S.notice("The file was downloaded")
			throw shortcutResponse(poemTextFile)
		},
		body = Text("Download")
	)

	def poemTextFile: LiftResponse = InMemoryResponse(
		data = poem.mkString("\n").getBytes("UTF-8"),
		headers = "Content-Type" -> "text/plain; charset=utf8" ::
			"Content-Disposition" -> "attachment; filename=\"poem.txt\"" :: Nil,
		cookies = Nil,
		200
	)
}
