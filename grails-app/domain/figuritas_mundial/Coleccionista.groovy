package figuritas_mundial

class Coleccionista {
    String nombre
    String nickname
    String email
    String contraseña
    Integer edad
    String ubicacion

    static constraints = {
        nombre blank: false, nullable: false
        nickname blank:false, nullable:false, unique: true
        email email: true, nullable: false, blank: false
        contraseña size:6..8, nullable: false
        edad min:18
        ubicacion nullable:true
    }
//    static hasMany = [posesionFiguritas: PosesionFigurita, intercambios: Intercambio]
    static hasMany = [posesionFiguritas: PosesionFigurita]

    String toString(){
        return nombre
    }
    String getDisplayString(){
        return nombre
    }

    def inicializar_posesion_figuritas(){
        def figuritas = Figurita.findAll()
        for (figurita in figuritas) {
            this.addToPosesionFiguritas(new PosesionFigurita(cantidad: 0, figurita: figurita))
        }
    }
}

