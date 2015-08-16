package griffin

import griffin.domain.AnimalTemplate
import griffin.domain.Classification
import griffin.services.AnimalTemplateService
import griffin.services.ClassificationService

class BootStrapKingdom {
	
	AnimalTemplateService animalTemplateService
	ClassificationService classificationService
	
	def addAnimalTemplate (String animalName, Classification classification, int maxHealth) {
		AnimalTemplate a = animalTemplateService.create animalName, classification, maxHealth
		return a
	}
	def addClassification (String classificationName) {
		Classification c = classificationService.create classificationName
		return c
	}

	def createTemplateLion () {
		return addAnimalTemplate (TEMPLATE_LION, ensureClassMammal(), 40)
	}
	def createTemplateOrca() {
		return addAnimalTemplate (TEMPLATE_ORCA, ensureClassFish(), 80)
	}
	def createTemplateVulture() {
		return addAnimalTemplate (TEMPLATE_VULTURE, ensureClassBird(), 60)
	}
	def createTemplateSnake() {
		return addAnimalTemplate (TEMPLATE_SNAKE, ensureClassReptile(), 20)
	}
	def ensureClassMammal () {
		if (mammal == null) {
			mammal = addClassification(CLASS_MAMMAL)
		}
		return mammal
	}
	def ensureClassFish () {
		if (fish == null) {
			fish = addClassification(CLASS_FISH)
		}
		return fish
	}
	def ensureClassBird () {
		if (bird == null) {
			bird = addClassification(CLASS_BIRD)
		}
		return bird
	}
	def ensureClassReptile () {
		if (reptile == null) {
			reptile = addClassification(CLASS_REPTILE)
		}
		return reptile
	}
	
	private Classification mammal;
	private Classification fish;
	private Classification bird;
	private Classification reptile;
		
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
