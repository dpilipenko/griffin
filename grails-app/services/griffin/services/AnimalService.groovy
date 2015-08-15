package griffin.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.transaction.Transactional
import griffin.domain.Animal
import griffin.domain.Classification

@Transactional
class AnimalService {

    def create(String name, Classification classification) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		if (classification == null) {
			return null;
		}
		Animal a = new Animal (name: name, classification: classification).save()
		if (a) {
			log.debug "Created animal: " + name
		} else {
			log.error "Error creating animal: animalName:" + name + " classification:" + classification
		}
    }
	
    private static final Log log = LogFactory.getLog(this)
}
