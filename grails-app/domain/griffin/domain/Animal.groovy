package griffin.domain

class Animal {
	AnimalTemplate template 
    static constraints = {
    }
	
	@Override
	public String toString() {
		return "Animal[template: "+template+"]"
	}
}
