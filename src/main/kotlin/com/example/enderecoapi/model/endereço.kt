package com.example.enderecoapi.model

import jakarta.persistence.*

@Entity
@Table(name = "enderecos")
data class Endereco(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val cep: String = "",

    val logradouro: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val uf: String = ""
)