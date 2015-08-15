package griffin.domain

class Army {
	static hasMany = [ soldiers: Animal ]
    static constraints = {
    }	
	@Override
	public String toString() {
		return "Army[soldiers: "+soldiers+"]"
	}
}
