package figuritas_mundial

class BootStrap {

    def init = { servletContext ->
        if (!Figurita.count()){
            createSampleData()
        }
    }
    def destroy = {
    }
    private createSampleData(){
        new Figurita(codigo:"ARG1",descripcion: "Martinez").save()
        new Figurita(codigo:"ARG2",descripcion: "Romero").save()
        new Figurita(codigo:"ARG3",descripcion: "Tagliafico").save()
        new Figurita(codigo:"ARG5",descripcion: "Paredes").save()
        new Figurita(codigo:"ARG10",descripcion: "Messi").save()
        new Figurita(codigo:"ARG11",descripcion: "Di Maria").save()

    }
}
