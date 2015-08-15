package griffin.domain

class Classification {
	String name
    static constraints = {
		name unique: true
    }
	@Override
	public String toString() {
		return "Classification[name: "+name+"]"
	}
}