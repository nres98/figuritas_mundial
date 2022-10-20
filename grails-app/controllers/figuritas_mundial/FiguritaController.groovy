package figuritas_mundial

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FiguritaController {

    FiguritaService figuritaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond figuritaService.list(params), model:[figuritaCount: figuritaService.count()]
    }

    def show(Long id) {
        respond figuritaService.get(id)
    }

    def create() {
        respond new Figurita(params)
    }

    def save(Figurita figurita) {
        if (figurita == null) {
            notFound()
            return
        }

        try {
            figuritaService.save(figurita)
        } catch (ValidationException e) {
            respond figurita.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'figurita.label', default: 'Figurita'), figurita.id])
                redirect figurita
            }
            '*' { respond figurita, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond figuritaService.get(id)
    }

    def update(Figurita figurita) {
        if (figurita == null) {
            notFound()
            return
        }

        try {
            figuritaService.save(figurita)
        } catch (ValidationException e) {
            respond figurita.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'figurita.label', default: 'Figurita'), figurita.id])
                redirect figurita
            }
            '*'{ respond figurita, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        figuritaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'figurita.label', default: 'Figurita'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'figurita.label', default: 'Figurita'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
