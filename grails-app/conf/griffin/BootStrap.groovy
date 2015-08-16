package griffin

import griffin.domain.Animal
import griffin.domain.AnimalTemplate
import griffin.domain.Army
import griffin.domain.Classification
import griffin.domain.Role
import griffin.domain.User
import griffin.domain.UserRole

import org.apache.commons.logging.LogFactory

class BootStrap {
	
	def animalService
	def animalTemplateService
	def armyService
	def classificationService
	def springSecurityService
	def userService
	
	/* shared members here */
	BootStrapKingdom bootStrapKingdom
	AnimalTemplate templateLion
	AnimalTemplate templateOrca
	AnimalTemplate templateVulture
	AnimalTemplate templateSnake
	Classification classMammal
	Classification classFish
	Classification classBird
	Classification classReptile
	
    def init = { servletContext ->	
		log.info "Begin initializing Griffin"
		
		bootStrapKingdom = new BootStrapKingdom(animalTemplateService: animalTemplateService, classificationService: classificationService)
		
		/* define Users */
		log.info "Begin defining Users"
		doUserStuff ()
		log.info "Complete defining Users"
		
		/* define Classifications */
		log.info "Begin defining Classifications"
		classMammal = bootStrapKingdom.ensureClassMammal ()
		classFish = bootStrapKingdom.ensureClassFish ()
		classBird = bootStrapKingdom.ensureClassBird ()
		classReptile = bootStrapKingdom.ensureClassReptile ()
		log.info "Complete defining Classifications"
		
		/* define Animal Templates */
		log.info "Begin defining Animal Templates"
		templateLion = bootStrapKingdom.createTemplateLion ()
		templateOrca = bootStrapKingdom.createTemplateOrca ()
		templateVulture = bootStrapKingdom.createTemplateVulture ()
		templateSnake = bootStrapKingdom.createTemplateSnake ()
		log.info "Complete defining Animal Templates"
		
		/* define Armies */
		log.info "Begin defining Armies"
		Army landArmy = createLandArmy ()
		Army otherArmy = createOtherArmy ()
		log.info "Complete defining Armies"
		
		log.info "Complete initializing Griffin"
    }
    def destroy = {
    }
	def doUserStuff () {
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		User bob = userService.registerAdmin("bob@robert.com", "secret")
	}
	def addAnimal (AnimalTemplate animalTemplate) {
		Animal a = animalService.create animalTemplate
		return a
	}
	def createLandArmy () {
		Set<Animal> recruits = new HashSet<Animal>()
		recruits.add addAnimal(templateLion)
		recruits.add addAnimal(templateLion)
		recruits.add addAnimal(templateLion)
		recruits.add addAnimal(templateSnake)
		recruits.add addAnimal(templateSnake)
		recruits.add addAnimal(templateSnake)
		Army army = armyService.create recruits
		return army
	}
	def createOtherArmy () {
		Set<Animal> recruits = new HashSet<Animal>()
		recruits.add addAnimal(templateVulture)
		recruits.add addAnimal(templateVulture)
		recruits.add addAnimal(templateVulture)
		recruits.add addAnimal(templateOrca)
		recruits.add addAnimal(templateOrca)
		recruits.add addAnimal(templateOrca)
		Army army = armyService.create recruits
		return army
	}
	
	private static final log = LogFactory.getLog(this)
}
	