package griffin

import griffin.domain.Classification
import org.apache.commons.logging.LogFactory

class BootStrap {
	def classificationService
	
    def init = { servletContext ->	
		/* define Classifications */
		log.info "Begin defining Classifications"
		addClassification "mammal"
		addClassification "fish"
		addClassification "bird"
		addClassification "reptile"
		log.info "Complete defining Classifications"
    }
    def destroy = {
    }
	
	def addClassification (String classificationName) {
		Classification c = classificationService.create(classificationName)
		return c
	}
	private static final log = LogFactory.getLog(this)
}
	