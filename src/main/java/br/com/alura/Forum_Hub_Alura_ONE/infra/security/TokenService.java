package br.com.alura.Forum_Hub_Alura_ONE.infra.security;

import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import br.com.alura.Forum_Hub_Alura_ONE.domain.users.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final SecretRepository secretRepository;
    private final UsuarioRepository usuarioRepository;

    public TokenService(SecretRepository secretRepository, UsuarioRepository usuarioRepository) {
        this.secretRepository = secretRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public String getSecret(String email, String senha) {
        var usuario = usuarioRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha não encontrados"));

        return secretRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Secret JWT não encontrado"))
                .getTokenSecret();
    }

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(getSecret(usuario.getEmail(), usuario.getSenha()));
            String token = JWT.create()
                    .withIssuer("API Fórum-Hub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
        return "";
    }

    public String getSubject(String tokenJWT, Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(getSecret(usuario.getEmail(), usuario.getSenha()));
            return JWT.require(algoritmo)
                    .withIssuer("API Fórum-Hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado: " +tokenJWT);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
