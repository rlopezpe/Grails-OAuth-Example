package cc.rolando.security.oauth

import org.scribe.model.Token

class OauthCallBackController {

    def index() { }
	def oauthService
	def grailsApplication
	def springSecurityService
 
	def twitter() {
		Token tAT = (Token) session[oauthService.findSessionKeyForAccessToken('twitter')]
		def resourceURL = "https://api.twitter.com/1.1/account/settings.json"
		def twitterResource = oauthService.getTwitterResource(tAT, resourceURL)
		def twitterResponse = JSON.parse(twitterResource?.getBody())
		log.debug("twitterResponse ****************" + twitterResponse)
		def twitterURL = "https://api.twitter.com/1.1/users/show.json?screen_name="
		def screenNameURL= twitterURL + twitterResponse['screen_name']
		def tResource = oauthService.getTwitterResource(twitterAccessToken, screenNameURL)
		def tResponse = JSON.parse(tResource?.getBody())
		log.debug("twitterResponseDetailed****************" + tResponse)
		String userName = twitterResponse['screen_name']
		String twitterId = twitterResponseDetailed['id']
		log.debug("twitterId***************" + twitterId)
		/*User user = User.findByTwitterId(twitterId)
		if (user) {
			// If user found with this twitter id, authenticate him
			springSecurityService.reauthenticate(userName)
		} else {
		   
		   Write code to redirect user to your sign up page.
		   Make sure you persist the facebook id for future use
		   
		}*/
	}
}
