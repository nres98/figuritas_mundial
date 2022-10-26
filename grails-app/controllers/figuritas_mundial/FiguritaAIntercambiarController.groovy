package figuritas_mundial

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FiguritaAIntercambiarController {

    FiguritaAIntercambiarService figuritaAIntercambiarService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond figuritaAIntercambiarService.list(params), model:[figuritaAIntercambiarCount: figuritaAIntercambiarService.count()]
    }

    def show(Long id) {
        respond figuritaAIntercambiarService.get(id)
    }

    def create() {
        respond new FiguritaAIntercambiar(params)
    }

    def save(FiguritaAIntercambiar figuritaAIntercambiar) {
        if (figuritaAIntercambiar == null) {
            notFound()
            return
        }

        try {
            figuritaAIntercambiarService.save(figuritaAIntercambiar)
        } catch (ValidationException e) {
            respond figuritaAIntercambiar.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'figuritaAIntercambiar.label', default: 'FiguritaAIntercambiar'), figuritaAIntercambiar.id])
                redirect figuritaAIntercambiar
            }
            '*' { respond figuritaAIntercambiar, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond figuritaAIntercambiarService.get(id)
    }

    def update(FiguritaAIntercambiar figuritaAIntercambiar) {
        if (figuritaAIntercambiar == null) {
            notFound()
            return
        }

        try {
            figuritaAIntercambiarService.save(figuritaAIntercambiar)
        } catch (ValidationException e) {
            respond figuritaAIntercambiar.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'figuritaAIntercambiar.label', default: 'FiguritaAIntercambiar'), figuritaAIntercambiar.id])
                redirect figuritaAIntercambiar
            }
            '*'{ respond figuritaAIntercambiar, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        figuritaAIntercambiarService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'figuritaAIntercambiar.label', default: 'FiguritaAIntercambiar'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'figuritaAIntercambiar.label', default: 'FiguritaAIntercambiar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
