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

    void actualizar_cantidad(cantidad_nueva){

        if (this.cantidad >0 && cantidad_nueva < 1){
            throw new Exception("Error: Se debe tener al menos 1 unidad de la figurita")
        }
        else{
            setCantidad(cantidad_nueva)
        }
    }

    //static belongsTo =[Coleccionista]
}
