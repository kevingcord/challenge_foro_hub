package com.forohub.controller;

import com.forohub.domain.Usuario;
import com.forohub.dto.DatosAutenticacionUsuario;
import com.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public String login(@RequestBody @Valid DatosAutenticacionUsuario datos){

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(datos.login(), datos.password());

        var authentication = manager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        return tokenService.generarToken(usuario);
    }
}