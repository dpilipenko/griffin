package griffin.filters

import grails.plugin.springsecurity.SpringSecurityService
import griffin.domain.User
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;

class LoggingFilters {

	SpringSecurityService springSecurityService
	
    def filters = {
        all(controller:'*', action:'*') {
            before = {
				User user = springSecurityService.currentUser
				log.debug "current user is: " + user + " controller action w/ this request: $request.requestURL ? $request.queryString"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
	
	private static final Log log = LogFactory.getLog(this)
}
