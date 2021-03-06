package griffin.controllers



import static org.springframework.http.HttpStatus.*

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.plugin.springsecurity.SpringSecurityService;
import grails.transaction.Transactional
import griffin.domain.Animal
import griffin.domain.AnimalTemplate
import griffin.domain.Army;
import griffin.domain.Profile
import griffin.domain.User
import griffin.services.ProfileService

@Transactional(readOnly = true)
class ArmyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	ProfileService profileService
	SpringSecurityService springSecurityService

    def index(Integer max) {
		User currentUser = springSecurityService.currentUser
		log.info "listing armies for: " + currentUser
		params.max = Math.min(max ?: 10, 100)
		Set<Army> armies = new HashSet<>()
		for (Profile profile : profileService.getProfiles(currentUser, params)) {
			armies.add(profile.army)
		}
		def armyInstanceList = armies.toList()
        respond armyInstanceList, model:[armyInstanceCount: armyInstanceList.size()]
    }

    def show(Army armyInstance) {
		log.info "showing: " + armyInstance
		Map<AnimalTemplate, Set<Animal>> resultMaps = mapOutTheArmy armyInstance
        respond armyInstance, model:[soldierMap: resultMaps]
    }

    def edit(Army armyInstance) {
        respond armyInstance
    }

    @Transactional
    def update(Army armyInstance) {
        if (armyInstance == null) {
            notFound()
            return
        }

        if (armyInstance.hasErrors()) {
            respond armyInstance.errors, view:'edit'
            return
        }

        armyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Army.label', default: 'Army'), armyInstance.id])
                redirect armyInstance
            }
            '*'{ respond armyInstance, [status: OK] }
        }
    }

	protected Map<AnimalTemplate, Set<Animal>> mapOutTheArmy (Army army) {
		Map<AnimalTemplate, Set<Animal>> retval = new HashMap<>()
		for (Animal soldier : army.soldiers) {
			AnimalTemplate template = soldier.template
			Set<Animal> soldierMates = retval.get(template)
			if (!soldierMates) {
				soldierMates = new HashSet<Animal> ()
			}
			soldierMates.add(soldier)
			retval.put(template, soldierMates)
		}
		return retval
	}
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'army.label', default: 'Army'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	private static final Log log = LogFactory.getLog(this)
}
