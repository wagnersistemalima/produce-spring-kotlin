package com.sistemalima.livraria.adapters.repositories

import com.sistemalima.livraria.domain.Livro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LivroRepository: JpaRepository<Livro, Long> {
}