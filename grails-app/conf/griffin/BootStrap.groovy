package griffin

import griffin.domain.Classification
import org.apache.commons.logging.LogFactory

class BootStrap {

	private static final log = LogFactory.getLog(this)
	
    def init = { servletContext ->
		
		/* define Classifications */
		addClassification "mammal"
		addClassification "fish"
		addClassification "bird"
		addClassification "reptile"
		log.info "Defined Classifications"
		
    }
    def destroy = {
    }
	
	def addClassification (String classificationName) {
		Classification c = new Classification(name: classificationName).save()
		if (c)
			log.debug "added classification: " + classificationName
		else
			log.error "failed to add classification: " + classificationName
	}
	
}
	