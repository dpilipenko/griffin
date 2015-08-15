package griffin

import griffin.domain.Animal
import griffin.domain.Army
import griffin.domain.Classification
import org.apache.commons.logging.LogFactory

class BootStrap {
	def animalService
	def classificationService
	
	/* shared members here */
	Classification classMammal;
	Classification classFish;
	
    def init = { servletContext ->	
		log.info "Begin initializing Griffin"
		
		/* define Classifications */
		log.info "Begin defining Classifications"
		classMammal = addClassification "mammal"
		classFish = addClassification "fish"
		Classification classBird = addClassification "bird"
		Classification classReptile = addClassification "reptile"
		log.info "Complete defining Classifications"
		
		/* define Animals */
		log.info "Begin defining Animals"
		Animal lion = createAnimalLion()
		Animal orca = createAnimalOrca()
		Animal vulture = addAnimal "vulture", classBird
		Animal snake = addAnimal "snake", classReptile
		log.info "Complete defining Animals"
		
		/* define Armies */
		log.info "Begin defining Armies"
		Army army = new Army().save();
		Set<Animal> armyRecruits = new HashSet<Animal>()
		armyRecruits.add(createAnimalLion())
		armyRecruits.add(createAnimalLion())
		armyRecruits.add(createAnimalLion())
		armyRecruits.add(createAnimalOrca())
		army.animals = armyRecruits
		if (!army.save()) {
			log.error "Problem saving the army"
		}
		
		log.info "animals size:" + army.animals.size()
		log.info "Complete defining Armies"
		
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
	
	Animal createAnimalLion () {
		return addAnimal ("lion", classMammal)
	}
	Animal createAnimalOrca () {
		return addAnimal ("orca", classFish)
	}
	
	private static final log = LogFactory.getLog(this)
}
	