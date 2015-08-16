package griffin.controllers



import static org.springframework.http.HttpStatus.*

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import griffin.domain.Profile;
import griffin.domain.User

@Transactional(readOnly = true)
class ProfileController {

    static allowedMethods = [update: "PUT"]
	
	SpringSecurityService springSecurityService

    def index(Integer max) {
		User currentUser = springSecurityService.currentUser
		log.info "Listing profiles for " + currentUser
        params.max = Math.min(max ?: 10, 100)
        respond Profile.findAllByUser(currentUser, params), model:[profileInstanceCount: Profile.count()]
    }

    def show(Profile profileInstance) {
        respond profileInstance
    }

    def edit(Profile profileInstance) {
        respond profileInstance
    }

    @Transactional
    def update(Profile profileInstance) {
        if (profileInstance == null) {
            notFound()
            return
        }

        if (profileInstance.hasErrors()) {
            respond profileInstance.errors, view:'edit'
            return
        }

        profileInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Profile.label', default: 'Profile'), profileInstance.id])
                redirect profileInstance
            }
            '*'{ respond profileInstance, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	private static final Log log = LogFactory.getLog(this)
}
