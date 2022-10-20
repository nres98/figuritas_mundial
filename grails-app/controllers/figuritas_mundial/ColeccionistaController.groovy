package figuritas_mundial

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ColeccionistaController {

    ColeccionistaService coleccionistaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def inicializar_posesion_figuritas(Coleccionista coleccionista){
        println("Nombre")
        println(coleccionista.nombre)
       def figuritas = Figurita.findAll()
    for (figurita in figuritas) {
        def posesion = new PosesionFigurita(cantidad: 0, figurita: figurita).save(flush: true, failOnError: true)
        coleccionista.addToPosesionFiguritas(posesion)
    }
        coleccionista.save(flush: true,failOnError: true)

}


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond coleccionistaService.list(params), model:[coleccionistaCount: coleccionistaService.count()]
    }

    def show(Long id) {
        respond coleccionistaService.get(id)
    }

    def create() {
        respond new Coleccionista(params)
    }

    def save(Coleccionista coleccionista) {
        if (coleccionista == null) {
            notFound()
            return
        }

        try {
            coleccionistaService.save(coleccionista)
        } catch (ValidationException e) {
            respond coleccionista.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'coleccionista.label', default: 'Coleccionista'), coleccionista.id])
                //redirect coleccionista
                redirect inicializar_posesion_figuritas(coleccionista)

            }
            '*' { respond coleccionista, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond coleccionistaService.get(id)
    }
    def actualizar_figuritas(Long id) {
        respond coleccionistaService.get(id)
    }

    def update(Coleccionista coleccionista) {
        if (coleccionista == null) {
            notFound()
            return
        }

        try {
            coleccionistaService.save(coleccionista)
        } catch (ValidationException e) {
            respond coleccionista.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'coleccionista.label', default: 'Coleccionista'), coleccionista.id])
                redirect coleccionista
            }
            '*'{ respond coleccionista, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        coleccionistaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'coleccionista.label', default: 'Coleccionista'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'coleccionista.label', default: 'Coleccionista'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
