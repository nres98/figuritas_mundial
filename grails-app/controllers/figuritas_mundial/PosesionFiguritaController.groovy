package figuritas_mundial

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PosesionFiguritaController {

    PosesionFiguritaService posesionFiguritaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Transactional
    def actualizar_muchos(){
        for (name in params.keySet()) {
            def match = name =~ /^posesionFigurita_(\d+)$/
            if (match) {
                def id = match[0][1] as long
                def posesionFigurita = PosesionFigurita.get(id)
                def cantidad = params.get(name) as int
                posesionFigurita.cantidad = cantidad
                posesionFigurita.save(flush: true, failOnError: true)
            }
        }
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond posesionFiguritaService.list(params), model:[posesionFiguritaCount: posesionFiguritaService.count()]
    }

    def show(Long id) {
        respond posesionFiguritaService.get(id)
    }

    def create() {
        respond new PosesionFigurita(params)
    }

    def save(PosesionFigurita posesionFigurita) {
        if (posesionFigurita == null) {
            notFound()
            return
        }

        try {
            posesionFiguritaService.save(posesionFigurita)
        } catch (ValidationException e) {
            respond posesionFigurita.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'posesionFigurita.label', default: 'PosesionFigurita'), posesionFigurita.id])
                redirect posesionFigurita
            }
            '*' { respond posesionFigurita, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond posesionFiguritaService.get(id)
    }

    def update(PosesionFigurita posesionFigurita) {
        if (posesionFigurita == null) {
            notFound()
            return
        }

        try {
            posesionFiguritaService.save(posesionFigurita)
        } catch (ValidationException e) {
            respond posesionFigurita.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'posesionFigurita.label', default: 'PosesionFigurita'), posesionFigurita.id])
                redirect posesionFigurita
            }
            '*'{ respond posesionFigurita, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        posesionFiguritaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'posesionFigurita.label', default: 'PosesionFigurita'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'posesionFigurita.label', default: 'PosesionFigurita'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
