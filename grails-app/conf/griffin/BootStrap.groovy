package griffin

import griffin.domain.Animal
import griffin.domain.Classification
import org.apache.commons.logging.LogFactory

class BootStrap {
	def animalService
	def classificationService
	
    def init = { servletContext ->	
		log.info "Begin initializing Griffin"
		
		/* define Classifications */
		log.info "Begin defining Classifications"
		Classification classMammal = addClassification "mammal"
		Classification classFish = addClassification "fish"
		Classification classBird = addClassification "bird"
		Classification classReptile = addClassification "reptile"
		log.info "Complete defining Classifications"
		
		/* define Animals */
		log.info "Begin defining Animals"
		Animal lion = addAnimal "lion", classMammal
		Animal orca = addAnimal "orca", classFish
		Animal vulture = addAnimal "vulture", classBird
		Animal snake = addAnimal "snake", classReptile
		log.info "Complete defining Animals"
		
		log.info "Complete initializing Griffin"
    }
    def destroy = {
    }
	
	def addAnimal (String animalName, Classification classification) {
		Animal a = animalService.create(animalName, classification)
		return a
	}
	def addClassification (String classificationName) {
		Classification c = classificationService.create(classificationName)
		return c
	}
	private static final log = LogFactory.getLog(this)
}
	