package griffin.services

import grails.transaction.Transactional
import griffin.domain.Army
import griffin.domain.Profile
import griffin.domain.Role;
import griffin.domain.User
import griffin.domain.UserRole;

@Transactional
class UserService {

	def registerUser (String username, String password) {
		User user = register (username, password)
		if (user) {
			log.info "Registered user: " + username
			user = assignRole user, ROLE_USER
		} else {
			log.error "Error registering user: " + username
		}
		return user
	}
	
	def registerAdmin (String username, String password) {
		User user = register (username, password)
		user = assignRole user, ROLE_USER
		user = assignRole user, ROLE_ADMIN
		return user
	}
	
	protected Profile createProfile (User user) {
		Army army = new Army().save(failOnTrue: true)
		Profile profile = new Profile()
		profile.user = user
		profile.army = army
		profile = profile.save(failOnTrue: true)
		return profile
	}
	
	protected User register (String username, String password) {
		User user = new User(
			username: username,
			password: password,
			enabled: true
			).save(failOnTrue: true)
		createProfile user
		return user
	}
	
	protected User assignRole (User user, String authority) {
		Role role = Role.findByAuthority(authority) ?: new Role(authority: authority).save(failOnError: true)
		UserRole.create user, role, true
		return user
	}
	
	protected final String ROLE_USER = "ROLE_USER"
	protected final String ROLE_ADMIN = "ROLE_ADMIN"
} 
