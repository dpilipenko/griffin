package griffin.domain

class Animal {
	
	String name
	Classification classification
	
    static constraints = {
		name required: true
		classification required: true
    }
	
	@Override
	public String toString() {
		return "Animal[name: "+name+" classification: "+ classification + "]"
	}
}
