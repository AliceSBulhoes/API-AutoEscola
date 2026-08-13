package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.infra.security.TokenService;
import br.com.fiap3esph.autoescola3esph.instrutor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
@RequiredArgsConstructor
public class InstrutorController {
    private final InstrutorRepository repository;
    private final TokenService tokenService;

    @PostMapping
    @Transactional
    public void cadastrarInstrutor(@RequestBody @Valid DadosCadastroInstrutor dados) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
    }

    @GetMapping
    public Page<DadosListagemInstrutor> listarInstrutores(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemInstrutor::new);
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoInstrutor detalharInstrutor(@PathVariable Long id){
        Instrutor instrutor = repository.getReferenceById(id);
        return new DadosDetalhamentoInstrutor(instrutor);
    }

    @PutMapping
    @Transactional
    public void atualizarIntrutores(
            @RequestBody @Valid DadosAtualizacaoInstrutor dados
    ){
        Instrutor instrutor = repository.getReferenceById(dados.id());

        instrutor.atualizarInformacoes(dados);

        repository.save(instrutor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarInstrutores(@PathVariable Long id){
        // ABOLIDO!
        // repository.deleteById(id);
        Instrutor instrutor = repository.getReferenceById(id);
        instrutor.excluir();
        repository.save(instrutor);

        return ResponseEntity.noContent().build();

    }
}
