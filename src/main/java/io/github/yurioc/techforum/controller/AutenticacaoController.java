package io.github.yurioc.techforum.controller;

import io.github.yurioc.techforum.domain.usuario.DadosAutenticacao;
import io.github.yurioc.techforum.domain.usuario.Usuario;
import io.github.yurioc.techforum.infra.security.DadosTokenJwt;
import io.github.yurioc.techforum.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) Objects.requireNonNull(authentication.getPrincipal()));
        return ResponseEntity.ok(new DadosTokenJwt(tokenJWT));
    }
}
