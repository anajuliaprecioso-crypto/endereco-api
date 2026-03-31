package com.example.enderecoapi.service

import com.example.enderecoapi.client.ViaCepClient
import com.example.enderecoapi.dto.EnderecoResponse
import com.example.enderecoapi.model.Endereco
import com.example.enderecoapi.repository.EnderecoRepository
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val repository: EnderecoRepository,
    private val viaCepClient: ViaCepClient
) {

    fun listar(): List<EnderecoResponse> =
        repository.findAll().map { it.toResponse() }

    fun buscarPorId(id: Long): EnderecoResponse =
        repository.findById(id)
            .orElseThrow { RuntimeException("Endereço com ID $id não encontrado") }
            .toResponse()

    fun buscarPorCep(cep: String): List<EnderecoResponse> =
        repository.findByCep(cep).map { it.toResponse() }

    fun buscarEsalvar(cep: String): EnderecoResponse {
        val dados = viaCepClient.buscarCep(cep)
            ?: throw RuntimeException("CEP $cep não encontrado")

        if (dados.erro == true)
            throw RuntimeException("CEP $cep inválido")

        val endereco = Endereco(
            cep = dados.cep ?: cep,
            logradouro = dados.logradouro ?: "",
            bairro = dados.bairro ?: "",
            cidade = dados.localidade ?: "",
            uf = dados.uf ?: ""
        )
        return repository.save(endereco).toResponse()
    }

    fun deletar(id: Long) = repository.deleteById(id)

    private fun Endereco.toResponse() = EnderecoResponse(
        id = id,
        cep = cep,
        logradouro = logradouro,
        bairro = bairro,
        cidade = cidade,
        uf = uf
    )
}
