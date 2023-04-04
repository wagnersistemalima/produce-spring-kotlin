package com.sistemalima.livraria.application.ports.output

import com.sistemalima.livraria.adapters.controller.dto.LivroResponseDTO
import com.sistemalima.livraria.domain.Livro

interface LivroUserCase {

    fun execute(livro: Livro): LivroResponseDTO
}