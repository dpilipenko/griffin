package griffin.domain

class Classification {

	String name
	
    static constraints = {
		name unique: true
    }
}
