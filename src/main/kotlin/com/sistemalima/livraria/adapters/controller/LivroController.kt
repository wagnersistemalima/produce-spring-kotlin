package com.sistemalima.livraria.adapters.controller

import com.sistemalima.livraria.adapters.controller.dto.LivroRequestDTO
import com.sistemalima.livraria.adapters.controller.dto.LivroResponseDTO
import com.sistemalima.livraria.adapters.controller.mapper.livroRequestDTOMapper
import com.sistemalima.livraria.adapters.sqs.NotificationProduce
import com.sistemalima.livraria.application.ports.output.LivroUserCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/livros"], produces = [MediaType.APPLICATION_JSON_VALUE])
class LivroController(
    @Autowired
    private val notificationProduce: NotificationProduce,
    @Autowired
    private val livroRequestDTOMapper: livroRequestDTOMapper,
    @Autowired
    private val livroUserCase: LivroUserCase
) {

    private val logger: Logger = LoggerFactory.getLogger(LivroController::class.java)

    @PostMapping
    fun create(@RequestBody livroRequestDTO: LivroRequestDTO): ResponseEntity<LivroResponseDTO> {

        logger.info("Inicio do processo request, class: LivroController, method: create")

        val livro = livroRequestDTOMapper.from(livroRequestDTO)

        val livroResponseDTO = livroUserCase.execute(livro)

        notification(livroResponseDTO.email, livroResponseDTO.titulo)

        logger.info("Fim do processo request, class: LivroController, method: create")

        return ResponseEntity.ok(livroResponseDTO)

    }


    private fun notification(email: String, titulo: String) {
        logger.info("Preparando a notificação do cadastro do livro, " +
                "class: LivroController, method: notification")
            notificationProduce.send(email, titulo, "O livro com esse titulo: $titulo, acaba de ser cadastrado")
    }
}