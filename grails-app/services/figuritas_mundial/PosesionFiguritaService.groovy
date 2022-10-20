package figuritas_mundial

import grails.gorm.services.Service

@Service(PosesionFigurita)
interface PosesionFiguritaService {

    PosesionFigurita get(Serializable id)

    List<PosesionFigurita> list(Map args)

    Long count()

    void delete(Serializable id)

    PosesionFigurita save(PosesionFigurita posesionFigurita)

}