package com.sistemalima.livraria.adapters.controller.mapper

import com.sistemalima.livraria.adapters.controller.dto.LivroRequestDTO
import com.sistemalima.livraria.domain.Livro
import org.springframework.stereotype.Component

@Component
class livroRequestDTOMapper {

    fun from(livroRequestDTO: LivroRequestDTO): Livro {
        return Livro(
            titulo = livroRequestDTO.titulo,
            autor = livroRequestDTO.autor,
            valor = livroRequestDTO.valor,
            email = livroRequestDTO.email
        )
    }
}