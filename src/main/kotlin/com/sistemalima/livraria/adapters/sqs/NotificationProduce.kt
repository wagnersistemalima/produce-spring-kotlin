package com.sistemalima.livraria.adapters.sqs

import com.google.gson.Gson
import com.sistemalima.livraria.adapters.sqs.dto.NotificationDTO
import com.sistemalima.livraria.adapters.sqs.enum.TypeNotificationEnum
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class NotificationProduce(
    @Value("\${cloud.aws.queue.name}")
    var fila : String
) {

    private val logger: Logger = LoggerFactory.getLogger(NotificationProduce::class.java)

    @Autowired
    private lateinit var queueMessageTemplate: QueueMessagingTemplate

    private val gson = Gson()

    fun send(anEmail: String, anTitulo: String, messagePayload: String) {

        logger.info("Entrando no produtor de mensagens, preparando a mensagem, " +
                "class: NotificationProduce, method: send")

        val notificationDTO = NotificationDTO(
            email = anEmail,
            tipo = TypeNotificationEnum.EMAIL,
            titulo = anTitulo,
            notification = messagePayload
        )

        val message = gson.toJson(notificationDTO)

        send(message)
    }


    private fun send(anMessage: String) {

        logger.info("Enviando a mensagem, class: NotificationProduce, method: send")

        val message = MessageBuilder.withPayload(anMessage).build()

        queueMessageTemplate.convertAndSend(fila, message)

        logger.info("Mensagem enviada com sucesso! $message, class: NotificationProduce, method: send")
    }

}