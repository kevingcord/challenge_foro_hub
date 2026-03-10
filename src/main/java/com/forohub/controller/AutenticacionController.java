package com.forohub.controller;

import com.forohub.domain.Usuario;
import com.forohub.dto.DatosAutenticacionUsuario;
import com.forohub.infra.security.TokenService;
import com.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public AutenticacionController(UsuarioRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public String login(@RequestBody @Valid DatosAutenticacionUsuario datos){

        Usuario usuario = repository.findByLogin(datos.login())
                .orElseThrow();

        String token = tokenService.generarToken(usuario);

        return token;
    }

}