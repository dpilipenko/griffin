package griffin.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Classification)
class ClassificationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "test creation"() {
		final String NAME = "MyNewClassification" 
		Classification c = new Classification(name: NAME)
		c = c.save()
		expect:
			c != null
    }
	
	def "test creation - no duplicates"() {
		final String NAME = "ClassificationDoNotDuplicateMe"
		Classification c1 = new Classification(name: NAME).save(flush: true)
		Classification c2 = new Classification(name: NAME).save(flush: true)
		expect:
			c1 && !c2 //expect that c1 saved and c2 failed because NAME was already taken 
	}
}
