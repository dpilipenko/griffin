package griffin.services

import grails.transaction.Transactional
import griffin.domain.Profile;
import griffin.domain.User

@Transactional
class ProfileService {

	Profile getProfile(User user, Map params) {
		def profiles = getProfiles(user, params)
		return profiles?.get(0)
	}
	
    def getProfiles(User user, Map params) {
		if (params) {
			return Profile.findAllByUser(user, params)
		} else {
			return Profile.findAllByUser(user)
		}
    }
}
