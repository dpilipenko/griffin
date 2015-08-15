package griffin.domain

class Animal {
	int health
	AnimalTemplate template 
    static constraints = {
		template required: true
    }
	@Override
	public String toString() {
		return "Animal[health: "+health+" template: "+template+"]"
	}
}
