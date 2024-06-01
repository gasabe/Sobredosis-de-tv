package ar.edu.unsam.algo2


class Programa {
    var presentadores = mutableListOf<Presentador>()
    var restricciones = mutableListOf<RetriccionPrograma>()
    val ratings = mutableListOf<Rating>()
    val presupuesto = 10000.0

    fun promedioDeRatings() = ratings.sortedBy { it.fecha }//ordena
        .takeLast(5)//toma los ultimos 5
        .map { it.puntaje } //trae(mapea) los 5 puntajes
        .average()//hace el promedio

    fun cantidadDePresentadores() = presentadores.size
    fun conducidoPor(nombrePresentador: String) =
        presentadores.any { presentador -> presentador.nombre == nombrePresentador }
    fun aRevisar() {
        val primeraRestriccion = restricciones.find { restriccion->!restriccion.seCumple(this) }

        primeraRestriccion?.ejecutarAcciones(this)//el ? es  llamada segura
    }

}