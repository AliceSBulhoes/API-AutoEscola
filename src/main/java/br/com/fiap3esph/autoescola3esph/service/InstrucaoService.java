package br.com.fiap3esph.autoescola3esph.service;

import br.com.fiap3esph.autoescola3esph.domain.agenda.*;
import br.com.fiap3esph.autoescola3esph.domain.agenda.validacao.ValidadorAgendamento;
import br.com.fiap3esph.autoescola3esph.domain.agenda.validacao.ValidadorInstrutorAtivo;
import br.com.fiap3esph.autoescola3esph.domain.aluno.Aluno;
import br.com.fiap3esph.autoescola3esph.domain.aluno.AlunoNotFoundException;
import br.com.fiap3esph.autoescola3esph.domain.aluno.AlunoRepository;
import br.com.fiap3esph.autoescola3esph.domain.instrutor.Instrutor;
import br.com.fiap3esph.autoescola3esph.domain.instrutor.InstrutorNotFoundException;
import br.com.fiap3esph.autoescola3esph.domain.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrucaoService {
    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadorAgendamento;

    @Transactional
    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados){
        if(!alunoRepository.existsById(dados.idAluno())){
            throw new AlunoNotFoundException("Id do aluno informado não existe!");
        }
        if(dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())){
            throw new InstrutorNotFoundException("Id do instrutor informado não existe!");
        }

        validadorAgendamento.forEach(validador -> validador.validar((dados)));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);
        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.dataHora());

        if(instrutor == null){
            throw new ValidacaoException("Não há instrutor disponível para a data/hora escolhida");
        }

        Instrucao salvo = repository.save(instrucao);

        return new DadosDetalhamentoAgendamento(salvo);
    }

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória, caso o Instrutor não seja informado!");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.dataHora());
    }
}
