package ar.edu.unsam.algo2

class Grilla {

    val programas = mutableListOf<Programa>()
    val programasArevicion = mutableListOf<Programa>()
    val observerNuevoPrograma = mutableListOf<ObserversNuevoPrograma>()


    fun agregarPrograma(programa: Programa) {
        programas.add(programa)
        observerNuevoPrograma.forEach{it.notificarNuevoPrograma(programa,this)}
    }

    fun borrarPrograma(programa: Programa) = programas.remove(programa)

    fun siguientePrograma(programa: Programa): Programa {
        val indiceDePrograma = programas.indexOf(programa)
        return if (programas.size > indiceDePrograma) programas[indiceDePrograma + 1] else programas[0]
    }

    fun agregarProgramaArevicion(programa: Programa) = programasArevicion.add(programa)

    fun procesoDeRevicion() = programasArevicion.forEach { programa -> programa.aRevisar(this) }
    fun sincronizarProgramas() {
        programasArevicion.removeAll { programaEnRevicion -> !programas.contains(programaEnRevicion)}
    }
}