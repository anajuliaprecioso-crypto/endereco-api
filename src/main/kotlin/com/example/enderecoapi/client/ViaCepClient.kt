package com.example.enderecoapi.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class ViaCepClient {

    private val client = WebClient.create("https://viacep.com.br")

    fun buscarCep(cep: String): ViaCepResponse? {
        return client.get()
            .uri("/ws/$cep/json/")
            .retrieve()
            .bodyToMono(ViaCepResponse::class.java)
            .block()
    }
}

data class ViaCepResponse(
    val cep: String? = null,
    val logradouro: String? = null,
    val bairro: String? = null,
    val localidade: String? = null,
    val uf: String? = null,
    val erro: Boolean? = false
)
