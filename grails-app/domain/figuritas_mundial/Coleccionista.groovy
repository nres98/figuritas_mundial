package figuritas_mundial

class Coleccionista {
    String nombre
    String email

    static constraints = {
        nombre blank: false, nullable: false
        email email: true, nullable: false, blank: false
    }
    static hasMany = [posesionFiguritas: PosesionFigurita]

    String toString(){
        return nombre
    }
    String getDisplayString(){
        return nombre
    }
}

