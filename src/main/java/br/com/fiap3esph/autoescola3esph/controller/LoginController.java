package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.usuario.DadosLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(token);
        return ResponseEntity.ok(token);
    }
}
