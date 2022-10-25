package figuritas_mundial

import grails.gorm.services.Service

@Service(FiguritaAIntercambiar)
interface FiguritaAIntercambiarService {

    FiguritaAIntercambiar get(Serializable id)

    List<FiguritaAIntercambiar> list(Map args)

    Long count()

    void delete(Serializable id)

    FiguritaAIntercambiar save(FiguritaAIntercambiar figuritaAIntercambiar)

}