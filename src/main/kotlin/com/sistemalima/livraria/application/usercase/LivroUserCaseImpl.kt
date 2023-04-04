package com.sistemalima.livraria.application.usercase

import com.sistemalima.livraria.adapters.controller.dto.LivroResponseDTO
import com.sistemalima.livraria.adapters.repositories.LivroRepository
import com.sistemalima.livraria.application.ports.output.LivroUserCase
import com.sistemalima.livraria.domain.Livro
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class LivroUserCaseImpl(
    @Autowired
    private val livroRepository: LivroRepository
): LivroUserCase {

    private val logger : Logger = LoggerFactory.getLogger(LivroUserCaseImpl::class.java)

    @Transactional
    override fun execute(livro: Livro): LivroResponseDTO {

        logger.info("Movimentação do processo request, preparando para salvar a entidade, " +
                "class: LivroUserCaseImpl,  method: execute")

        val livroEntity = livroRepository.save(livro)

        logger.info("Movimentação do processo request, entidade salva com sucesso! " +
                "class: LivroUserCaseImpl,  method: execute")

        return LivroResponseDTO(livroEntity)
    }
}