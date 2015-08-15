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
	AnimalTemplate templateLion;
	AnimalTemplate templateOrca;
	AnimalTemplate templateVulture;
	AnimalTemplate templateSnake;
	Classification classMammal;
	Classification classFish;
	Classification classBird;
	Classification classReptile;
	
    def init = { servletContext ->	
		log.info "Begin initializing Griffin"
		
		/* define Classifications */
		log.info "Begin defining Classifications"
		classMammal = addClassification CLASS_MAMMAL
		classFish = addClassification CLASS_FISH
		classBird = addClassification CLASS_BIRD
		classReptile = addClassification CLASS_REPTILE
		log.info "Complete defining Classifications"
		
		/* define Animal Templates */
		log.info "Begin defining Animal Templates"
		templateLion = createTemplateLion ()
		templateOrca = createTemplateOrca ()
		templateVulture = createTemplateVulture ()
		templateSnake = createTemplateSnake ()
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
	def addClassification (String classificationName) {
		Classification c = classificationService.create classificationName
		return c
	}
	def addAnimalTemplate (String animalName, Classification classification, int maxHealth) {
		AnimalTemplate a = animalTemplateService.create animalName, classification, maxHealth
		return a
	}
	def createTemplateLion () {
		return addAnimalTemplate (TEMPLATE_LION, classMammal, 40)
	}
	def createTemplateOrca() {
		return addAnimalTemplate (TEMPLATE_ORCA, classFish, 80)
	}
	def createTemplateVulture() {
		return addAnimalTemplate (TEMPLATE_VULTURE, classBird, 60)
	}
	def createTemplateSnake() {
		return addAnimalTemplate (TEMPLATE_SNAKE, classReptile, 20)
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
	/* string constants here */
	private static final String CLASS_MAMMAL = "mammal" 
	private static final String CLASS_FISH = "fish"
	private static final String CLASS_BIRD = "bird"
	private static final String CLASS_REPTILE = "reptile"
	private static final String TEMPLATE_LION = "templateLion"
	private static final String TEMPLATE_ORCA = "templateOrca"
	private static final String TEMPLATE_VULTURE = "templateVulture"
	private static final String TEMPLATE_SNAKE = "templateSnake"
}
	