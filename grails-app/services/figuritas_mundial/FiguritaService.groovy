package figuritas_mundial

import grails.gorm.services.Service

@Service(Figurita)
interface FiguritaService {

    Figurita get(Serializable id)

    List<Figurita> list(Map args)

    Long count()

    void delete(Serializable id)

    Figurita save(Figurita figurita)

}