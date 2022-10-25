package figuritas_mundial

class Intercambio {
    String estado
//    Coleccionista coleccionista1
//    Coleccionista coleccionista2

    static constraints = {estado nullable: false
    }
    static belongsTo = [coleccionista1:Coleccionista,coleccionista2:Coleccionista]
    static hasMany = [figuritasAIntercambiar: FiguritaAIntercambiar]




}
