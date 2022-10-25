package figuritas_mundial

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class IntercambioController {

    IntercambioService intercambioService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

   /* @Transactional
    def inicializar_intercambio(Intercambio intercambio){
        def coleccionista1 = intercambio.coleccionista1
        coleccionista1.addToIntercambios(intercambio)
        coleccionista1.save(flush: true,failOnError: true)
        println("Agregado correctamente 1")
        println(coleccionista1)
        def coleccionista2 = intercambio.coleccionista2
        coleccionista2.addToIntercambios(intercambio)
        coleccionista2.save(flush: true,failOnError: true)
        println("Agregado correctamente 2")
        println(coleccionista2)
        intercambio.save(flush: true,failOnError: true)

    }*/

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond intercambioService.list(params), model:[intercambioCount: intercambioService.count()]
    }

    def show(Long id) {
        respond intercambioService.get(id)
    }

    def create() {
        respond new Intercambio(params)
    }

    def save(Intercambio intercambio) {
        if (intercambio == null) {
            notFound()
            return
        }

        try {
            intercambioService.save(intercambio)
        } catch (ValidationException e) {
            respond intercambio.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'intercambio.label', default: 'Intercambio'), intercambio.id])
                redirect intercambio
//                redirect inicializar_intercambio(intercambio)
            }
            '*' { respond intercambio, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond intercambioService.get(id)
    }

    def update(Intercambio intercambio) {
        if (intercambio == null) {
            notFound()
            return
        }

        try {
            intercambioService.save(intercambio)
        } catch (ValidationException e) {
            respond intercambio.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'intercambio.label', default: 'Intercambio'), intercambio.id])
                redirect intercambio
            }
            '*'{ respond intercambio, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        intercambioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'intercambio.label', default: 'Intercambio'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'intercambio.label', default: 'Intercambio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
