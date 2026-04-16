package br.com.fiap3esph.autoescola3esph.endereco;

public record DadosEndereco(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep
) {

}
