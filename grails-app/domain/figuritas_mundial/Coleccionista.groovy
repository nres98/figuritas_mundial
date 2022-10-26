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
    static hasMany = [posesionFiguritas: PosesionFigurita, intercambios: Intercambio]

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
    def agregar_intercambio(Intercambio intercambio){
        println("entro a agregar intercambio")
        if (!this.intercambios.contains(intercambio) ){
            this.addToIntercambios(intercambio)
        }
    }

    def calcular_match(Coleccionista otro_coleccionista){
        // Dada la informacion de otro coleccionista, calcula cuantas figuritas pueden intercambiar
    }

    def porcentaje_competado(){
        // Devuelve el porcentaje de completitud del album (para cuantas Posesion Figurita tiene más de 1) / Cant de Figuritas
    }


    def ofrecer_intercambio(Intercambio intercambio){
        // cambia estado de Intercambio a ofrecido
    }
    def aceptar_intercambio(Intercambio intercambio){
        // cambia estado de Intercambio a aceptado y realiza el intercambio de figuritas
    }

    def rechazar_intercambio(Intercambio intercambio){
        // cambia estado de Intercambio a rechazado
    }
}

