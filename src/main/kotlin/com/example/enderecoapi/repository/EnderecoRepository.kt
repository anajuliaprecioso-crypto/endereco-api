package com.example.enderecoapi.repository

import com.example.enderecoapi.model.Endereco
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnderecoRepository : JpaRepository<Endereco, Long> {

    fun findByCep(cep: String): List<Endereco>

    fun findByUf(uf: String): List<Endereco>

    fun findByCidade(cidade: String): List<Endereco>
}
