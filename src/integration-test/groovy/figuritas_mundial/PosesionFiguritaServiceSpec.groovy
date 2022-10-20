package figuritas_mundial

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PosesionFiguritaServiceSpec extends Specification {

    PosesionFiguritaService posesionFiguritaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PosesionFigurita(...).save(flush: true, failOnError: true)
        //new PosesionFigurita(...).save(flush: true, failOnError: true)
        //PosesionFigurita posesionFigurita = new PosesionFigurita(...).save(flush: true, failOnError: true)
        //new PosesionFigurita(...).save(flush: true, failOnError: true)
        //new PosesionFigurita(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //posesionFigurita.id
    }

    void "test get"() {
        setupData()

        expect:
        posesionFiguritaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PosesionFigurita> posesionFiguritaList = posesionFiguritaService.list(max: 2, offset: 2)

        then:
        posesionFiguritaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        posesionFiguritaService.count() == 5
    }

    void "test delete"() {
        Long posesionFiguritaId = setupData()

        expect:
        posesionFiguritaService.count() == 5

        when:
        posesionFiguritaService.delete(posesionFiguritaId)
        sessionFactory.currentSession.flush()

        then:
        posesionFiguritaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PosesionFigurita posesionFigurita = new PosesionFigurita()
        posesionFiguritaService.save(posesionFigurita)

        then:
        posesionFigurita.id != null
    }
}
