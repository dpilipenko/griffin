package griffin.services

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import grails.transaction.Transactional
import griffin.domain.Animal
import griffin.domain.AnimalTemplate
import griffin.domain.Classification;

@Transactional
class AnimalService {

	def create(AnimalTemplate template) {
		if (template == null) {
			return null;
		}
		Animal a = new Animal (template: template).save()
		if (a) {
			log.debug "Create animal: " + a
		} else {
			log.error "Error creating animal: template:" + template
		}
		return a
	}
	
	private static final Log log = LogFactory.getLog(this)
}
