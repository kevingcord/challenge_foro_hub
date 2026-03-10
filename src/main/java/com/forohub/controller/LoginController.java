package com.forohub.controller;

import com.forohub.infra.security.DatosAutenticacionUsuario;
import com.forohub.infra.security.DatosJWTToken;
import com.forohub.infra.security.TokenService;

import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public DatosJWTToken autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos){

        var authToken = new UsernamePasswordAuthenticationToken(
                datos.login(),
                datos.password()
        );

        var authentication = authenticationManager.authenticate(authToken);

        var usuario = (com.forohub.domain.Usuario) authentication.getPrincipal();

        var token = tokenService.generarToken(usuario);

        return new DatosJWTToken(token);
    }

}