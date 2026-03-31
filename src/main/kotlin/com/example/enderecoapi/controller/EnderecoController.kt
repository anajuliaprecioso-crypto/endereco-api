package com.example.enderecoapi.controller

import com.example.enderecoapi.dto.EnderecoRequest
import com.example.enderecoapi.dto.EnderecoResponse
import com.example.enderecoapi.service.EnderecoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "Operações com endereços")
class EnderecoController(
    private val service: EnderecoService
) {

    @GetMapping
    @Operation(summary = "Lista todos os endereços salvos")
    fun listar(): List<EnderecoResponse> = service.listar()

    @GetMapping("/{id}")
    @Operation(summary = "Busca endereço por ID")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    fun buscarPorId(@PathVariable id: Long): EnderecoResponse =
        service.buscarPorId(id)

    @PostMapping("/buscar-cep")
    @Operation(summary = "Busca CEP no ViaCEP e salva no banco")
    fun buscarEsalvar(@RequestBody request: EnderecoRequest): EnderecoResponse =
        service.buscarEsalvar(request.cep)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove um endereço")
    fun deletar(@PathVariable id: Long) = service.deletar(id)
}
