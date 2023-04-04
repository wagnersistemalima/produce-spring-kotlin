package com.sistemalima.livraria.adapters.controller.dto

import com.sistemalima.livraria.domain.Livro
import java.math.BigDecimal

data class LivroResponseDTO(
    val id: Long?,
    val titulo: String,
    val autor: String,
    val valor: BigDecimal,
    val email: String
){
    constructor(livro: Livro): this (livro.id, livro.titulo, livro.autor, livro.valor, livro.email)
}
