package figuritas_mundial

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ColeccionistaServiceSpec extends Specification {

    ColeccionistaService coleccionistaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Coleccionista(...).save(flush: true, failOnError: true)
        //new Coleccionista(...).save(flush: true, failOnError: true)
        //Coleccionista coleccionista = new Coleccionista(...).save(flush: true, failOnError: true)
        //new Coleccionista(...).save(flush: true, failOnError: true)
        //new Coleccionista(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //coleccionista.id
    }

    void "test get"() {
        setupData()

        expect:
        coleccionistaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Coleccionista> coleccionistaList = coleccionistaService.list(max: 2, offset: 2)

        then:
        coleccionistaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        coleccionistaService.count() == 5
    }

    void "test delete"() {
        Long coleccionistaId = setupData()

        expect:
        coleccionistaService.count() == 5

        when:
        coleccionistaService.delete(coleccionistaId)
        sessionFactory.currentSession.flush()

        then:
        coleccionistaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Coleccionista coleccionista = new Coleccionista()
        coleccionistaService.save(coleccionista)

        then:
        coleccionista.id != null
    }
}
