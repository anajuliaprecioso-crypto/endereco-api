package com.example.enderecoapi.dto

data class EnderecoRequest(
    val cep: String
)

data class EnderecoResponse(
    val id: Long,
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val uf: String
)
