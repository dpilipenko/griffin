package griffin.services

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;

import grails.transaction.Transactional
import griffin.domain.Classification

@Transactional
class ClassificationService {
	
    def create(String classificationName) {
		Classification c = new Classification(name: classificationName).save()
		if (c)
			log.debug "created classification: " + classificationName
		else
			log.error "failed to create classification: " + classificationName
		return c;
    }
	
    private static final Log log = LogFactory.getLog(this)
}
