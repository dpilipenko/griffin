package griffin.domain

class AnimalTemplate {
	
	String commonName
	Classification classification
	
    static constraints = {
		commonName required: true
		classification required: true
    }
	
	@Override
	public String toString() {
		return "AnimalTemplate[commonName: "+commonName+" classification: "+ classification + "]"
	}
}
