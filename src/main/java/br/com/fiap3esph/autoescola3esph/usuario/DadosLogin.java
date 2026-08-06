package br.com.fiap3esph.autoescola3esph.usuario;

import jakarta.validation.constraints.NotBlank;

import java.awt.*;

public record DadosLogin(
        @NotBlank
        String login,

        @NotBlank
        String senha
) {
}
