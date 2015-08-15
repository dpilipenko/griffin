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
		classMammal = addClassification MAMMAL
		classFish = addClassification FISH
		classBird = addClassification BIRD
		classReptile = addClassification REPTILE
		log.info "Complete defining Classifications"
		
		/* define Animal Templates */
		log.info "Begin defining Animal Templates"
		lion = addAnimalTemplate LION, classMammal
		orca = addAnimalTemplate ORCA, classFish
		vulture = addAnimalTemplate VULTURE, classBird
		snake = addAnimalTemplate SNAKE, classReptile
		log.info "Complete defining Animal Templates"
		
		/* define Armies */
		log.info "Begin defining Armies"
		Army landArmy = createLandArmy()
		Army otherArmy = createOtherArmy()
		log.info "Complete defining Armies"
		
		log.info "Complete initializing Griffin"
    }
    def destroy = {
    }
	
	def addAnimal (AnimalTemplate animalTemplate) {
		Animal a = animalService.create animalTemplate
		return a
	}
	def addAnimalTemplate (String animalName, Classification classification) {
		AnimalTemplate a = animalTemplateService.create animalName, classification
		return a
	}
	def addClassification (String classificationName) {
		Classification c = classificationService.create classificationName
		return c
	}
	def createLandArmy () {
		Set<Animal> recruits = new HashSet<Animal>()
		recruits.add addAnimal(lion)
		recruits.add addAnimal(lion)
		recruits.add addAnimal(lion)
		recruits.add addAnimal(snake)
		recruits.add addAnimal(snake)
		recruits.add addAnimal(snake)
		Army army = armyService.create recruits
		return army
	}
	def createOtherArmy () {
		Set<Animal> recruits = new HashSet<Animal>()
		recruits.add addAnimal(vulture)
		recruits.add addAnimal(vulture)
		recruits.add addAnimal(vulture)
		recruits.add addAnimal(orca)
		recruits.add addAnimal(orca)
		recruits.add addAnimal(orca)
		Army army = armyService.create recruits
		return army
	}
	
	private static final log = LogFactory.getLog(this)
	/* string constants here */
	private static final String MAMMAL = "mammal" 
	private static final String FISH = "fish"
	private static final String BIRD = "bird"
	private static final String REPTILE = "reptile"
	private static final String LION = "lion"
	private static final String ORCA = "orca"
	private static final String VULTURE = "vulture"
	private static final String SNAKE = "snake"
}
	