package br.com.fiap3esph.autoescola3esph.instrutor;

import br.com.fiap3esph.autoescola3esph.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroInstrutor(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "O email precisa ser válido (ex: exemplo@gmail.com)")
        String email,

        @NotBlank(message = "CNH é obrigatorio")
        @Pattern(regexp = "[0-9]{9,11}")
        String cnh,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "Especialidade é obrigatório")
        Especialidade especialidade,

        @Valid
        DadosEndereco endereco
) {

}
