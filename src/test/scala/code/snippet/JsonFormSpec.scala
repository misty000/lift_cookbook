package code.snippet

import org.specs2.mutable.Specification


/**
 * Created with IntelliJ IDEA.
 * User: Misty
 * Date: 13-3-15
 * Time: 下午8:30
 */
class JsonFormSpec extends Specification {
	"jsonform" should {
		"aaa" in {
			val form =
				<div id="formToJson" name="formToJson">
					<input type="text" name="name" value="Royal Society"/>
					<input type="text" name="motto" value="Nullius in verba"/>
					<input type="submit" name="sb" value="go!"/>
				</div>
			val jf = new JsonForm
			val csssel = jf.render
			val r = csssel(form)
			println(r)
		}
	}
}
