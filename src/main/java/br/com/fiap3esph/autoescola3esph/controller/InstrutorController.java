package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.instrutor.DadosListagemInstrutor;
import br.com.fiap3esph.autoescola3esph.instrutor.Instrutor;
import br.com.fiap3esph.autoescola3esph.instrutor.DadosCadastroInstrutor;
import br.com.fiap3esph.autoescola3esph.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
    @Autowired
    private InstrutorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarInstrutor(@RequestBody DadosCadastroInstrutor dados) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
    }

    @GetMapping
    public Page<DadosListagemInstrutor> listarInstrutores(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return repository .findAll(paginacao).map(DadosListagemInstrutor::new);
    }
}
