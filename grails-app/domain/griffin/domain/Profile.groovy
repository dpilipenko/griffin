package griffin.domain

class Profile {
	static belongsTo = [ user: User ]
	Army army
	
    static constraints = {
		user required: true
    }
}
