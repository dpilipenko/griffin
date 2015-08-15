package griffin

import griffin.domain.Animal
import griffin.domain.AnimalTemplate
import griffin.domain.Army
import griffin.domain.Classification
import org.apache.commons.logging.LogFactory

class BootStrap {
	def animalService
	def animalTemplateService
	def armyService
	def classificationService
	
	/* shared members here */
	AnimalTemplate lion;
	AnimalTemplate orca;
	AnimalTemplate vulture;
	AnimalTemplate snake;
	Classification classMammal;
	Classification classFish;
	Classification classBird;
	Classification classReptile;
	
    def init = { servletContext ->	
		log.info "Begin initializing Griffin"
		
		/* define Classifications */
		log.info "Begin defining Classifications"
		classMammal = addClassification "mammal"
		classFish = addClassification "fish"
		classBird = addClassification "bird"
		classReptile = addClassification "reptile"
		log.info "Complete defining Classifications"
		
		/* define Animal Templates */
		log.info "Begin defining Animal Templates"
		lion = addAnimalTemplate "lion", classMammal
		orca = addAnimalTemplate "orca", classFish
		vulture = addAnimalTemplate "vulture", classBird
		snake = addAnimalTemplate "snake", classReptile
		log.info "Complete defining Animal Templates"
		
		/* define Armies */
		log.info "Begin defining Armies"
		Set<Animal> armyRecruits = new HashSet<Animal>()
		armyRecruits.add(createMeALion())
		armyRecruits.add(createMeALion())
		armyRecruits.add(createMeALion())
		armyRecruits.add(createMeALion())
		Army army = armyService.create armyRecruits
		log.info "Complete defining Armies"
		
		log.info "Complete initializing Griffin"
    }
    def destroy = {
    }
	
	def addAnimalTemplate (String animalName, Classification classification) {
		AnimalTemplate a = animalTemplateService.create(animalName, classification)
		return a
	}
	def addClassification (String classificationName) {
		Classification c = classificationService.create(classificationName)
		return c
	}
	Animal createMeALion() {
		Animal a = animalService.create(lion)
		return a
	}
	
	private static final log = LogFactory.getLog(this)
}
	