package figuritas_mundial

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class IntercambioServiceSpec extends Specification {

    IntercambioService intercambioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Intercambio(...).save(flush: true, failOnError: true)
        //new Intercambio(...).save(flush: true, failOnError: true)
        //Intercambio intercambio = new Intercambio(...).save(flush: true, failOnError: true)
        //new Intercambio(...).save(flush: true, failOnError: true)
        //new Intercambio(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //intercambio.id
    }

    void "test get"() {
        setupData()

        expect:
        intercambioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Intercambio> intercambioList = intercambioService.list(max: 2, offset: 2)

        then:
        intercambioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        intercambioService.count() == 5
    }

    void "test delete"() {
        Long intercambioId = setupData()

        expect:
        intercambioService.count() == 5

        when:
        intercambioService.delete(intercambioId)
        sessionFactory.currentSession.flush()

        then:
        intercambioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Intercambio intercambio = new Intercambio()
        intercambioService.save(intercambio)

        then:
        intercambio.id != null
    }
}
