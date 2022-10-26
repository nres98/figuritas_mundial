package figuritas_mundial

class Intercambio {
    String estado
    Coleccionista coleccionista1
    Coleccionista coleccionista2

    static constraints = {estado nullable: false
    }
    //static belongsTo = [coleccionista1:Coleccionista,coleccionista2:Coleccionista]
    static hasMany = [figuritasAIntercambiar: FiguritaAIntercambiar]

    void inicializar(){
        println("Entro a inicializar")
        def coleccionista1 = this.coleccionista1
        def coleccionista2 = this.coleccionista2
        println(coleccionista1)
        println(coleccionista2)
        coleccionista1.addToIntercambios(this)
//        coleccionista1.save(flush: true,failOnError: true)
        coleccionista2.addToIntercambios(this)
//        coleccionista2.save(flush: true,failOnError: true)
        println("Salio de inicializar")
    }


}
