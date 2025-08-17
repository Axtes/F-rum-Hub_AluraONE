package br.com.alura.Forum_Hub_Alura_ONE.controller;

import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import br.com.alura.Forum_Hub_Alura_ONE.domain.users.UsuarioRepository;
import br.com.alura.Forum_Hub_Alura_ONE.dto.UsuarioDTO;
import br.com.alura.Forum_Hub_Alura_ONE.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var usuarioOpt = usuarioRepository.findByEmail(dados.email());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body(new DadosTokenJWT("Usuário não encontrado"));
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            return ResponseEntity.status(401).body(new DadosTokenJWT("Senha inválida"));
        }

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid DadosAutenticacao dados) {
        if (usuarioRepository.findByEmail(dados.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        var usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }


}
