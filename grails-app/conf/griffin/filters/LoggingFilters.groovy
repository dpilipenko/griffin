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
				log.trace "controller action w/ this request: $request.requestURL ? $request.queryString"
				User user = springSecurityService.currentUser
				log.trace "current user is: " + user
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
	
	private static final Log log = LogFactory.getLog(this)
}
