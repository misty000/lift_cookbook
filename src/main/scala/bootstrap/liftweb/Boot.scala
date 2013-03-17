package bootstrap.liftweb

import net.liftweb._

import common._
import common.Full
import http._
import http.BadResponse
import http.Html5Properties
import http.NotFoundAsTemplate
import http.ParsePath
import http.ResponseWithReason
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._
import sitemap.Loc.If
import util.NamedPF
import code.lib.Customised
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException
import code.rest._


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
	private val logger = Logger(getClass)

	def boot {
		// where to search snippet
		LiftRules.addToPackages("code")
		LiftRules.addToPackages("com.pocketchangeapp")

		// REST
		LiftRules.dispatch.append(code.rest.AjaxFileUpload)

		// Build SiteMap
		val entries = List(
			Menu.i("Home") / "index", // the simple way to declare a menu

			// more complex because this menu allows anything in the
			// /static path to be visible
			Menu(Loc("Static", Link(List("static"), true, "/static/index"),
				"Static Content")))

		// set the sitemap.  Note if you don't want access control for
		// each page, just comment this line out.
		//LiftRules.setSiteMap(SiteMap(entries:_*))

		//Show the spinny image when an Ajax call starts
		LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

		// Make the spinny image go away when it ends
		LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

		// Force the request to be UTF-8
		LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

		// Use HTML5 for rendering
		LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

		//Init the jQuery module, see http://liftweb.net/jquery for more information.
		LiftRules.jsArtifacts = JQueryArtifacts
		JQueryModule.InitParam.JQuery = JQueryModule.JQuery172
		JQueryModule.init()

		// 404
		LiftRules.uriNotFound.prepend(NamedPF("404handler") {
			case (req, failure) =>
				NotFoundAsTemplate(ParsePath(List("404"), "html", true, false))
		})

		//		LiftRules.responseTransformers.append {
		//			case resp if resp.toResponse.code == 403 => CustomStatus.my403 openOr resp
		//			case resp => resp
		//		}

		//		LiftRules.responseTransformers.append {
		//			case resp if resp.toResponse.code == 403 => RedirectResponse("/403.html")
		//			case resp => resp
		//		}

		LiftRules.responseTransformers.append {
			case Customised(resp) => resp
			case resp => resp
		}
		val Protected = If(() => false, () => ForbiddenResponse("No!"))
		Menu.i("secret") / "secret" >> Protected // all requests to /secret return a 403

		// File upload
		//		LiftRules.maxMimeFileSize = 1024 * 1024
		//		LiftRules.maxMimeSize = 1024 * 1024
		LiftRules.handleMimeFile = OnDiskFileParamHolder.apply
		LiftRules.exceptionHandler.prepend {
			// fileupload size limit
			case (_, _, e: SizeLimitExceededException) =>
				println(e)
				ResponseWithReason(BadResponse(), "Unable to process file. Too large?")
		}
		//		def progressPrinter(bytesRead: Long, contentLength: Long, fieldIndex: Int) {
		//			println("Read %d of %d for %d" format(bytesRead, contentLength, fieldIndex))
		//		}
		//		LiftRules.progressListener = progressPrinter

		// REST
		// DRY URLs
		DryURLs.init()

		// Missing File Suffix
		MissingFileSuffix.init()

		// Missing .com from Email Addresses
		//		LiftRules.explicitlyParsedSuffixes = Helpers.knownSuffixes &~ (Set("com"))
		MissingDotCom.init()

		// Failing to Match on a File Suffix
		LiftRules.explicitlyParsedSuffixes += "csv"
		FailingToMatchOnFileSuffix.init()

		// Accept binary data in a REST service
		AcceptBinaryDataInRESTService.init()

		// Returning JSON
		ReturningJSON.init()

		// Google Sitemap
		Sitemap.init()

		// Debugging a Request
		LiftRules.onBeginServicing.append {
			case r@Req("paypal" :: _, _, _) => logger.debug(r)
			case r => logger.debug("Received: " + r)
		}
		LiftRules.liftRequest.append {
			case Req("robots" :: _, _, _) => false
		}
		//order
		//		LiftRules.early
		//		LiftRules.onBeginServicing
		//		LiftRules.earlyInStateless
		//		LiftRules.earlyInStateful


		LiftRules.siteMapFailRedirectLocation = "static" :: "permistion" :: Nil

		// Force HTTPS Requests
		LiftRules.earlyResponse.append(req => {
			if (req.request.scheme != "https") {
				val uriAndQuery = req.uri + (req.request.queryString.map(s => "?" + s) openOr "")
				val uri = "https://%s%s".format(req.request.serverName, uriAndQuery)
				Full(PermRedirectResponse(uri, req, req.cookies: _*))
			}
			else Empty
		})
	}
}