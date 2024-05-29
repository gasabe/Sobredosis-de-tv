package ar.edu.unsam.algo2.turistas

class Turista {
    val destinosDeseados = mutableListOf<Destino>()
    val destinosVisitados = mutableListOf<Destino>()
    lateinit var tipoTurista: TipoTurista
    var felicidad = 100
    
    fun cumplirSuenio() {
        val destino = tipoTurista.elegirDestino(this.destinosDeseados)
        destino.visitar(this)
        destinosVisitados.add(destino)
        destinosDeseados.remove(destino)
    }

    fun subirFelicidad(cantidad: Int) {
        felicidad += cantidad
    }
}


interface TipoTurista {
    fun elegirDestino(destinos: List<Destino>): Destino
}


class TuristaAnsioso : TipoTurista {
    override fun elegirDestino(destinos: List<Destino>) =
        destinos.first()
}


class TuristaAmbicioso : TipoTurista {
    override fun elegirDestino(destinos: List<Destino>) =
        destinos.maxBy { it.calificacion() }
}


interface Destino {
    fun visitar(turista: Turista)
    fun calificacion(): Int
}


class DestinoTuristico(var calificacion: Int = 1, var cantidadVisitas: Int = 0) : Destino {
    override fun visitar(turista: Turista) {
        cantidadVisitas += 1
        turista.subirFelicidad(calificacion * 3)
    }

    override fun calificacion() = calificacion
}


class ComboDestinos : Destino {
    val destinos = mutableListOf<Destino>()

    override fun visitar(turista: Turista) {
        destinos.forEach { it.visitar(turista) }
    }

    override fun calificacion() =
        destinos.sumOf { it.calificacion() }
}
