package figuritas_mundial

import grails.gorm.services.Service

@Service(Coleccionista)
interface ColeccionistaService {

    Coleccionista get(Serializable id)

    List<Coleccionista> list(Map args)

    Long count()

    void delete(Serializable id)

    Coleccionista save(Coleccionista coleccionista)

}