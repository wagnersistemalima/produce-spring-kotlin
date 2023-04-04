package com.sistemalima.livraria.adapters.sqs.dto

import com.sistemalima.livraria.adapters.sqs.enum.TypeNotificationEnum

data class NotificationDTO(
    val email: String,
    val tipo: TypeNotificationEnum,
    val titulo: String,
    val notification: String
)
