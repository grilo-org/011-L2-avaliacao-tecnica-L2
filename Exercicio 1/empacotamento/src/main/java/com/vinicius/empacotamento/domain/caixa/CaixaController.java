package com.vinicius.empacotamento.domain.caixa;

import com.vinicius.empacotamento.domain.dimensoes.Dimensao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caixas")
public class CaixaController {

    private final CaixaRepository caixaRepository;

    public CaixaController(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
    }

    @PostMapping
    public ResponseEntity<Caixa> criar(@RequestBody @Valid DadosCadastroCaixa dto) {
        Caixa caixa = new Caixa();
        caixa.setNome(dto.nome());
        caixa.setDimensao(new Dimensao(dto.dimensoes().altura(), dto.dimensoes().largura(), dto.dimensoes().comprimento()));

        Caixa salva = caixaRepository.save(caixa);
        return ResponseEntity.ok(salva);
    }
}
