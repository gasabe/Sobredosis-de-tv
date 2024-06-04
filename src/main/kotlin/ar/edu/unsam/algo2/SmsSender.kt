package ar.edu.unsam.algo2

interface SmsSender {
    fun sendSms(sms: Sms)
}
data class Sms(val to: Int, val content: String)
