package figuritas_mundial

class Figurita {
    String codigo
    String descripcion
    static constraints = {
        codigo blank:false, nullable:false
        descripcion blank:false, nullable:false
    }
    String toString(){
//        return "Figurita de $codigo (id: $id)"
    return codigo
    }
    String getDisplayString(){
        return codigo
    }
}
