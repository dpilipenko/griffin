package griffin.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.transaction.Transactional
import griffin.domain.AnimalTemplate
import griffin.domain.Classification

@Transactional
class AnimalTemplateService {

    def create(String commonName, Classification classification, int maxHealth) {
		if (commonName == null || commonName.isEmpty()) {
			return null;
		}
		if (classification == null) {
			return null;
		}
		if (maxHealth <= 0) {
			return null;
		}
		AnimalTemplate a = new AnimalTemplate (commonName: commonName, classification: classification, maxHealth: maxHealth).save()
		if (a) {
			log.debug "Created animal template: " + a
		} else {
			log.error "Error creating animal template: commonName:" + commonName + " classification:" + classification +" maxHealth: " + maxHealth
		}
		return a
    }
	
    private static final Log log = LogFactory.getLog(this)
}
