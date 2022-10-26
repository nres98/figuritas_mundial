package figuritas_mundial

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ColeccionistaSpec extends Specification implements DomainUnitTest<Coleccionista> {

    def setup() {
    }

    def cleanup() {
    }

    def "Guardar un coleccionista correctamente" (){
        given: "Se crea un nuevo coleccionista"
        def col = new Coleccionista(nombre: 'juan', nickname: 'j01',email: 'juan@mail.com',contraseña: '1234567',edad: 25,ubicacion: 'Caballito')

        when: "Se guarda el coleccionista"
        col.save()

        then: "El guardado es exitoso y se puede encontrar al coleccionista en la base de datos"
        col.errors.errorCount == 0
        col.id != null
        Coleccionista.get(col.id).nickname == col.nickname
    }

    def "Actualizar informacion de un coleccionista existente, se realiza correctamente" (){
        given: "Existe un coleccionista"
        def col = new Coleccionista(nombre: 'juan', nickname: 'j01',email: 'juan@mail.com',contraseña: '1234567',edad: 25,ubicacion: 'Caballito')
        col.save(failOnError:true)

        when: "Se actualiza su informacion"
        def coleccionista_guardado = Coleccionista.get(col.id)
        coleccionista_guardado.nickname = 'juan02'
        coleccionista_guardado.save(failOnError)

        then: "La actualizacion de informacion es exitosa, y los cambios se ven reflejados en la base de datos"

        Coleccionista.get(coleccionista_guardado.id).nickname == 'juan02'
    }
    def "Guardar un coleccionista con contraseña menor a tamaño solicitado causa error" (){
        given: "Se crea un nuevo coleccionista con contraseña corta"
        def col = new Coleccionista(nombre: 'juan', nickname: 'j01',email: 'juan@mail.com',contraseña: '1234',edad: 25,ubicacion: 'Caballito')

        when: "Se valida la informacion del coleccionista"
        col.validate()

        then: "Se notifica el error y no se guarda el coleccionista"
        col.hasErrors()
        "size.toosmall" == col.errors.getFieldError("contraseña").code

    }

    def "Guardar un coleccionista con edad menor a 18 causa error" (){
        given: "Se crea un nuevo coleccionista con contraseña corta"
        def col = new Coleccionista(nombre: 'juan', nickname: 'j01',email: 'juan@mail.com',contraseña: '1234567',edad: 16,ubicacion: 'Caballito')

        when: "Se valida la informacion del coleccionista"
        col.validate()

        then: "Se notifica el error y no se guarda el coleccionista"
        col.hasErrors()
        "min.notmet" == col.errors.getFieldError("edad").code

    }

    def "Agregar posesiones figuritas a un coleccionista correctamente" (){
        given: "Se crea un nuevo coleccionista"
        def col = new Coleccionista(nombre: 'juan', nickname: 'j01',email: 'juan@mail.com',contraseña: '1234567',edad: 25,ubicacion: 'Caballito')
        col.save(failOnError: true)


        when: "Se agregan PosesionFigurita al coleccionista"

        col.addToPosesionFiguritas(new PosesionFigurita(cantidad: 0,figurita: (new Figurita(codigo: 'ARG10',descripcion: 'Messi'))))
        col.addToPosesionFiguritas(new PosesionFigurita(cantidad: 0,figurita: (new Figurita(codigo: 'ARG1',descripcion: 'Martinez'))))

        then: "El coleccionista tiene vinculadas las posesiones de sus figuritas"
        2 == Coleccionista.get(col.id).posesionFiguritas.size()
    }



}
