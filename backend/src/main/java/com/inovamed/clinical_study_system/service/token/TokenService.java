package com.inovamed.clinical_study_system.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.inovamed.clinical_study_system.exception.MissingSecretKeyException;
import com.inovamed.clinical_study_system.exception.TokenGenerationException;
import com.inovamed.clinical_study_system.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService implements ITokenService {

    @Value("${api.security.token.secret:valorPadrao}")
    private String secret;

    @Override
    public String generateToken(User user) {
        if (secret == null || secret.isEmpty()) {
            throw new MissingSecretKeyException();
        }
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("INOVAMED")
                    .withSubject(user.getEmail()).withClaim("userId", user.getId())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException(exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256((secret));
            return JWT.require(algorithm).withIssuer("INOVAMED").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenGenerationException(exception);
        }
    }

    public String getEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("INOVAMED")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenGenerationException(exception);
        }
    }

    public Long getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("INOVAMED")
                    .build()
                    .verify(token)
                    .getClaim("userId").asLong();
        } catch (JWTVerificationException exception) {
            throw new TokenGenerationException(exception);
        }
    }
    // Configura a data de expiração como, por exemplo, 1 hora a partir do momento atual
    public Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
