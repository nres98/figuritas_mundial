package figuritas_mundial

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PosesionFiguritaSpec extends Specification implements DomainUnitTest<PosesionFigurita> {

    def setup() {
    }

    def cleanup() {
    }

    def "Guardar una Posesion Figurita correctamente" (){
        given: "Se crea una nueva Posesion Figurita"
        def pos_fig = new PosesionFigurita(cantidad: 0,figurita: new Figurita(codigo: 'ARG10',descripcion: 'Messi'))

        when: "Se guarda la Posesion Figurita"
        pos_fig.save()

        then: "El guardado es exitoso y se puede encontrar a la Posesion Figurita en la base de datos"
        pos_fig.errors.errorCount == 0
        pos_fig.id != null
        PosesionFigurita.get(pos_fig.id).cantidad == 0
    }

   def "Actualizar cantidad de una Posesion Figruita existente, se realiza correctamente" (){
        given: "Existe una Posesion Figurita"
        def pos_fig = new PosesionFigurita(cantidad: 0,figurita: new Figurita(codigo: 'ARG10',descripcion: 'Messi'))
        pos_fig.save(failOnError:true)

        when: "Se actualiza su cantidad"
        def posesion_guardada = PosesionFigurita.get(pos_fig.id)
        posesion_guardada.actualizar_cantidad(5)
        posesion_guardada.save(failOnError)

        then: "La actualizacion de informacion es exitosa, y los cambios se ven reflejados en la base de datos"

        PosesionFigurita.get(posesion_guardada.id).cantidad == 5
    }

    def "Disminuir a 0 la cantidad de una Posesion Figruita existente, que tenia cantidad > 0, lanza excepcion" (){
        given: "Existe una Posesion Figurita"
        def pos_fig = new PosesionFigurita(cantidad: 3,figurita: new Figurita(codigo: 'ARG10',descripcion: 'Messi'))
        pos_fig.save(failOnError:true)

        when: "Se disminuye su cantidad a 0"
        def posesion_guardada = PosesionFigurita.get(pos_fig.id)
        posesion_guardada.actualizar_cantidad(0)


        then: "Se lanza una excepcion"
        def exception = thrown(Exception)
        exception.message == "Error: Se debe tener al menos 1 unidad de la figurita"
    }

}
