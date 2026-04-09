package br.com.fiap3esph.autoescola3esph.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping
    public String healtCheck() {
        return "Verificação de integridde da API da Auto Escola 3ESPH";
    }
}
