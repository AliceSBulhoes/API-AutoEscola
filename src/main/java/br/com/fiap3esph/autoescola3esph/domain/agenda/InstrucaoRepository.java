package br.com.fiap3esph.autoescola3esph.domain.agenda;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    boolean existsByInstrutorIdAndDatahora(Long idInstrutor, LocalDateTime datahora);

    boolean existsByAlunoIdAndDatahoraBetween(Long idAluno, LocalDateTime inicioExpediente, LocalDateTime fimExpediente);
}
