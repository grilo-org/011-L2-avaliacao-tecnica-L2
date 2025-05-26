package com.vinicius.empacotamento.controller;

import com.vinicius.empacotamento.domain.caixa.Caixa;
import com.vinicius.empacotamento.domain.caixa.CaixaRepository;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoRequestDTO;
import com.vinicius.empacotamento.domain.empacotamento.EmpacotamentoResponseDTO;
import com.vinicius.empacotamento.service.EmpacotamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empacotamento")
@Tag(name = "Empacotamento", description = "Operações de empacotamento de pedidos")
public class EmpacotamentoController {

    @Autowired
    private EmpacotamentoService empacotamentoService;

    @Autowired
    private CaixaRepository caixaRepository;

    @PostMapping
    @Operation(summary = "Empacotar pedidos",
            description = "Calcula a melhor forma de empacotar os produtos dos pedidos nas caixas disponíveis")
    @ApiResponse(responseCode = "200", description = "Pedidos empacotados com sucesso",
            content = @Content(schema = @Schema(implementation = EmpacotamentoResponseDTO.class)))
    public ResponseEntity<EmpacotamentoResponseDTO> empacotarPedidos(
            @RequestBody @Valid EmpacotamentoRequestDTO request) {

        List<Caixa> caixasDisponiveis = caixaRepository.findAll();
        EmpacotamentoResponseDTO response = empacotamentoService.empacotarPedidos(request, caixasDisponiveis);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/caixas")
    @Operation(summary = "Listar caixas disponíveis",
            description = "Retorna todas as caixas disponíveis para empacotamento")
    public ResponseEntity<List<Caixa>> listarCaixas() {
        return ResponseEntity.ok(caixaRepository.findAll());
    }
}