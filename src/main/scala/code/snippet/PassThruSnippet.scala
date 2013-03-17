package code.snippet

import util.Random
import net.liftweb.util.Helpers._
import net.liftweb.util.PassThru

/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 上午11:24
 */
class PassThruSnippet {
	private def fiftyFifty = Random.nextBoolean()

	def render =
		if (fiftyFifty) "*" #> "Congratulations! The content was changed"
		else PassThru
}
