package ar.edu.unsam.algo2

import java.time.DayOfWeek


class Programa {
    var presentadores = mutableListOf<Presentador>()
    var restricciones = mutableListOf<RetriccionPrograma>()
    val ratings = mutableListOf<Rating>()
    var presupuesto = 10000.0
    var duracion = 20.0
    var sponsors = mutableListOf<String>()
    var titulo = " "
    var dias = mutableListOf<DayOfWeek>()

    fun promedioDeRatings() = ratings.sortedBy { it.fecha }//ordena
        .takeLast(5)//toma los ultimos 5
        .map { it.puntaje } //trae(mapea) los 5 puntajes
        .average()//hace el promedio

    fun cantidadDePresentadores() = presentadores.size
    fun conducidoPor(nombrePresentador: String) =
        presentadores.any { presentador -> presentador.nombre == nombrePresentador }

    fun aRevisar(grilla: Grilla) {
        val primeraRestriccion = restricciones.find { restriccion -> !restriccion.seCumple(this) }
        primeraRestriccion?.ejecutarAcciones(this, grilla)//el ? es  llamada segura
    }

    fun mitadDePresentadores() = presentadores.take(presentadores.size / 2)

    fun segundaMitadRestante() = presentadores.minus(mitadDePresentadores().toSet())
    fun mitadDelPresupuesto() = presupuesto / 2
    fun mitadDeDuracion() = duracion / 2
    fun tituloEnPalabras() = titulo.split("")
    fun presentadorPrincipal(): Presentador = presentadores[0] //Determina que el presentador 0 es el principal

    fun agregarPresentador(presentador: Presentador) { presentadores.add(presentador)}

}



