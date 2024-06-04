package ar.edu.unsam.algo2

abstract class RetriccionPrograma {//template method
    val acciones = mutableListOf<Acciones>()
    abstract fun seCumple(programa: Programa): Boolean

    fun ejecutarAcciones(programa: Programa, grilla: Grilla) {
        acciones.forEach { accion -> accion.ejecutar(programa,grilla) }
    }

}

class RatingMinimo(val minimoRating: Int) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = programa.promedioDeRatings() < minimoRating
}

class MaximosConductoresPrincipales(val maximoCantidad: Int) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = programa.cantidadDePresentadores() < maximoCantidad
}

class ConductorEspesifico(val presentador: String) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = programa.conducidoPor(presentador)
}

class MaximoPresupuesto(val presupuesto: Float) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = programa.presupuesto < presupuesto
}

class RestriccionOrCompuesta(val restricciones: List<RetriccionPrograma>) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = restricciones.any { it.seCumple(programa) }
}

class RestriccionAndCompuesta(val restricciones: List<RetriccionPrograma>) : RetriccionPrograma() {
    override fun seCumple(programa: Programa) = restricciones.all { it.seCumple(programa) }
}

