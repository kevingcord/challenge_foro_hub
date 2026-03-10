package com.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.forohub.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generarToken(Usuario usuario){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("forohub")
                .withSubject(usuario.getLogin())
                .withExpiresAt(fechaExpiracion())
                .sign(algorithm);
    }

    public String getSubject(String token){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm)
                .withIssuer("forohub")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant fechaExpiracion(){
        return Instant.now().plusSeconds(86400)
                .atZone(ZoneOffset.of("-05:00"))
                .toInstant();
    }
}