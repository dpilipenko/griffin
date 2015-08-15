package griffin.domain

class Animal {
	
	String name
	Classification classification
	
    static constraints = {
		name required: true
		classification required: true
    }
}
