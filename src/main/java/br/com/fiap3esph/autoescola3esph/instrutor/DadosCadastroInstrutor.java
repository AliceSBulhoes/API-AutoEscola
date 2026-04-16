package br.com.fiap3esph.autoescola3esph.instrutor;

import br.com.fiap3esph.autoescola3esph.endereco.DadosEndereco;

public record DadosCadastroInstrutor(
        String nome,
        String email,
        String cnh,
        Especialidade especialidade,
        DadosEndereco endereco
) {

}
