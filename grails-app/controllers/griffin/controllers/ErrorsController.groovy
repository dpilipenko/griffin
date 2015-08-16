package griffin.controllers

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class ErrorsController {

    def notFound () {
		log.error "HTTP 404 Error Occurred"
	}
	
	private static final Log log = LogFactory.getLog(this)
}
