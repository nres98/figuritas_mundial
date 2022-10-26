package figuritas_mundial

import grails.gorm.services.Service

@Service(Intercambio)
interface IntercambioService {

    Intercambio get(Serializable id)

    List<Intercambio> list(Map args)

    Long count()

    void delete(Serializable id)

    Intercambio save(Intercambio intercambio)

}