package griffin.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.transaction.Transactional
import griffin.domain.Animal
import griffin.domain.AnimalTemplate;
import griffin.domain.Army

@Transactional
class ArmyService {

	def create(Set<Animal> soldiers) {
		if (soldiers == null) {
			return null;
		}
		Army a = new Army(soldiers: soldiers).save()
		if (a) {
			log.debug "Create army: " + a
		} else {
			log.error "Error creating army: soldiers:" + soldiers
		}
		return a
	}
	
    private static final Log log = LogFactory.getLog(this)
}
