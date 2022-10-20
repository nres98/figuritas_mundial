package figuritas_mundial

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FiguritaServiceSpec extends Specification {

    FiguritaService figuritaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Figurita(...).save(flush: true, failOnError: true)
        //new Figurita(...).save(flush: true, failOnError: true)
        //Figurita figurita = new Figurita(...).save(flush: true, failOnError: true)
        //new Figurita(...).save(flush: true, failOnError: true)
        //new Figurita(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //figurita.id
    }

    void "test get"() {
        setupData()

        expect:
        figuritaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Figurita> figuritaList = figuritaService.list(max: 2, offset: 2)

        then:
        figuritaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        figuritaService.count() == 5
    }

    void "test delete"() {
        Long figuritaId = setupData()

        expect:
        figuritaService.count() == 5

        when:
        figuritaService.delete(figuritaId)
        sessionFactory.currentSession.flush()

        then:
        figuritaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Figurita figurita = new Figurita()
        figuritaService.save(figurita)

        then:
        figurita.id != null
    }
}
