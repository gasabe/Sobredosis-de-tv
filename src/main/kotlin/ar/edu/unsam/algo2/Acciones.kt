package ar.edu.unsam.algo2

interface Acciones {
    fun ejecutar(programa: Programa, grilla: Grilla)
}

class DividirPrograma() : Acciones {
    override fun ejecutar(programa: Programa, grilla: Grilla) {
        val programa1 = Programa().apply {
            presentadores = programa.mitadDePresentadores().toMutableList()
            presupuesto = programa.mitadDelPresupuesto()
            sponsors = programa.sponsors
            duracion = programa.mitadDeDuracion()
            titulo = "${programa.tituloEnPalabras()[0]} en el aire!"
            dias = dias
        }
        val programa2 = Programa().apply {
            presentadores = programa.segundaMitadRestante().toMutableList()
            presupuesto = programa.mitadDelPresupuesto()
            sponsors = programa.sponsors
            duracion = programa.mitadDeDuracion()
            titulo = "${programa.tituloEnPalabras()[1]} en el aire!"
            dias = programa.dias
        }
        grilla.borrarPrograma(programa)
        grilla.agregarPrograma(programa1)
        grilla.agregarPrograma(programa2)
    }
}

class CambiarPrograma() : Acciones {
    override fun ejecutar(programa: Programa, grilla: Grilla) {
        val nuevoPresentador = Presentador(nombre = "troy mcliure", mail = "troy@gmail.com")
        val nuevoPresupuesto = 100000.0
        val nuevoSponsor = mutableListOf("matt groening")
        val nuevoTitulo = "la casita de los horrores"
        val nuevaDuracion = 21.0
        val programaSimpson = Programa().apply {
            presentadores = mutableListOf(nuevoPresentador)
            presupuesto = nuevoPresupuesto
            sponsors = nuevoSponsor
            duracion = nuevaDuracion
            titulo = nuevoTitulo
            dias = programa.dias
        }
        grilla.borrarPrograma(programa)
        grilla.agregarPrograma(programaSimpson)
    }
}

class FusionarPrograma() : Acciones {

    fun elegirPrograma(primerPrograma: Programa, segundoPrograma: Programa) =
        if (elegirPrimero()) primerPrograma else segundoPrograma

    fun elegirTitulo() = if (elegirPrimero()) "Impacto total" else "Un buen día"
    private fun elegirPrimero() = (Math.random() * 100) > 50
    override fun ejecutar(programa: Programa, grilla: Grilla) {
        val siguientePrograma = grilla.siguientePrograma(programa)
        val programaFusion = Programa().apply {
            presentadores = mutableListOf(programa.presentadorPrincipal(), siguientePrograma.presentadorPrincipal())
            presupuesto = Math.min(programa.presupuesto, siguientePrograma.presupuesto)
            sponsors = elegirPrograma(programa, siguientePrograma).sponsors
            duracion = programa.duracion + siguientePrograma.duracion
            titulo = elegirTitulo()
            dias = programa.dias
        }
        grilla.borrarPrograma(programa)
        grilla.borrarPrograma(siguientePrograma)
        grilla.agregarPrograma(programaFusion)
    }
}