package figuritas_mundial

class PosesionFigurita {
    Integer cantidad
    Figurita figurita

    static constraints = {
        cantidad blank:false, min:0
    }
    String toString(){
//        return "Figurita de $codigo (id: $id)"
        return figurita
    }
    String getDisplayString(){
        return figurita
    }

    //static belongsTo =[Coleccionista]
}
