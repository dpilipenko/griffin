package griffin.domain

class BaseAnimal {
	
	String name
	Classification classification
	
    static constraints = {
		name required: true
		classification required: true
    }
}
