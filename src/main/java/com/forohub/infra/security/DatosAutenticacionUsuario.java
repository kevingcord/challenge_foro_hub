package com.forohub.infra.security;

public record DatosAutenticacionUsuario(
        String login,
        String password
) {
}