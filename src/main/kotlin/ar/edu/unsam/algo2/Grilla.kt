package ar.edu.unsam.algo2

class Grilla {

    val programas = mutableListOf<Programa>()
    val programasArevicion = mutableListOf<Programa>()


    fun agregarPrograma(programa: Programa) = programas.add(programa)
    fun borrarPrograma(programa: Programa) = programas.remove(programa)

    fun siguientePrograma(programa: Programa): Programa {
        val indiceDePrograma = programas.indexOf(programa)
        return if (programas.size > indiceDePrograma) programas[indiceDePrograma + 1] else programas[0]
    }

}