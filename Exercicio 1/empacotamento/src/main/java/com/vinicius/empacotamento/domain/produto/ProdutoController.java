package com.vinicius.empacotamento.domain.produto;

import com.vinicius.empacotamento.domain.dimensoes.Dimensao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody @Valid DadosCadastroProduto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDimensao(new Dimensao(dto.dimencoes().altura(), dto.dimencoes().largura(), dto.dimencoes().comprimento()));

        Produto salvo = produtoRepository.save(produto);
        return ResponseEntity.ok(salvo);
    }
}
