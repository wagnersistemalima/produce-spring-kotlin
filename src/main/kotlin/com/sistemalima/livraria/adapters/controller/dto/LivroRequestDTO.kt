package com.sistemalima.livraria.adapters.controller.dto

import java.math.BigDecimal

data class LivroRequestDTO(
    val titulo: String,
    val autor: String,
    val valor: BigDecimal,
    val email: String
)
