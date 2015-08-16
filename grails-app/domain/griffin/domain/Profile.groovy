package griffin.domain

class Profile {

	String displayName
	String tagline
	Army army
	static belongsTo = [ user: User ]
    static constraints = {
		displayName nullable: true
		tagline nullable: true
		user required: true
    }
	@Override
	public String toString() {
		return "Profile[" + user?.id + " | " + displayName + " | " + tagline +"]"
	}
}
