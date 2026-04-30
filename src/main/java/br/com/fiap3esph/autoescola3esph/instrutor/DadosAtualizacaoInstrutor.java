package br.com.fiap3esph.autoescola3esph.instrutor;

import br.com.fiap3esph.autoescola3esph.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoInstrutor(
        @NotNull
        Long id,
        String nome,
        String email,
        Especialidade especialidade,
        DadosEndereco endereco
) {

}
