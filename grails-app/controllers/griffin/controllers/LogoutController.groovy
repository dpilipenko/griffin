package griffin.controllers

import grails.plugin.springsecurity.annotation.Secured

class LogoutController {

	@Secured(["ROLE_USER"])
    def index() { }
}
