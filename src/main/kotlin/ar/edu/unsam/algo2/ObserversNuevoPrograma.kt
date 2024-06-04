package ar.edu.unsam.algo2

interface ObserversNuevoPrograma {
    fun notificarNuevoPrograma(programa: Programa, grilla: Grilla)
}
class NotificaPresentador : ObserversNuevoPrograma {
    lateinit var mailSender: MailSender
    override fun notificarNuevoPrograma(programa: Programa, grilla: Grilla) {
        programa.mailDeConductores().forEach {
            mailSender.sendMail(
                Mail(
                    from = "canalDeTv@empresas.com",
                    to = it,
                    subject = "Oportunidad!",
                    content = "Fuiste seleccionado para conducir ${programa.titulo}! Ponete en contacto con la gerencia."
                )
            )
        }
    }
}

class NotificacionPresupuestoAlto(var montoLimite: Double) : ObserversNuevoPrograma {
    lateinit var smsSender: SmsSender
    var cliowin = 1547283721
    override fun notificarNuevoPrograma(programa: Programa, grilla: Grilla) {
        if (programa.presupuesto > montoLimite) {
            smsSender.sendSms(
                Sms(
                    to = cliowin,
                    content = "${programa.presupuesto} - ${programa.titulo} - CONSEGUIR SPONSOR URGENTE!"
                )
            )
        }
    }
}
class EliminaProgramasAjenosAlaGrilla(): ObserversNuevoPrograma {
    override fun notificarNuevoPrograma(programa: Programa, grilla: Grilla) {
        grilla.sincronizarProgramas()
    }
}