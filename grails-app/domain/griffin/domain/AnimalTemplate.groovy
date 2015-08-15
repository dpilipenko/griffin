package griffin.domain

class AnimalTemplate {	
	String commonName
	Classification classification
	int maxHealth
    static constraints = {
		commonName required: true
		classification required: true
		maxHealth required: true
    }
	@Override
	public String toString() {
		return "AnimalTemplate[commonName: "+commonName+" classification: "+ classification + " maxHealth: "+maxHealth +"]"
	}
}
