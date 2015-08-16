package griffin.controllers



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import griffin.domain.Army;

@Transactional(readOnly = true)
class ArmyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Army.list(params), model:[armyInstanceCount: Army.count()]
    }

    def show(Army armyInstance) {
        respond armyInstance
    }

    def create() {
        respond new Army(params)
    }

    @Transactional
    def save(Army armyInstance) {
        if (armyInstance == null) {
            notFound()
            return
        }

        if (armyInstance.hasErrors()) {
            respond armyInstance.errors, view:'create'
            return
        }

        armyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'army.label', default: 'Army'), armyInstance.id])
                redirect armyInstance
            }
            '*' { respond armyInstance, [status: CREATED] }
        }
    }

    def edit(Army armyInstance) {
        respond armyInstance
    }

    @Transactional
    def update(Army armyInstance) {
        if (armyInstance == null) {
            notFound()
            return
        }

        if (armyInstance.hasErrors()) {
            respond armyInstance.errors, view:'edit'
            return
        }

        armyInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Army.label', default: 'Army'), armyInstance.id])
                redirect armyInstance
            }
            '*'{ respond armyInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Army armyInstance) {

        if (armyInstance == null) {
            notFound()
            return
        }

        armyInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Army.label', default: 'Army'), armyInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'army.label', default: 'Army'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
