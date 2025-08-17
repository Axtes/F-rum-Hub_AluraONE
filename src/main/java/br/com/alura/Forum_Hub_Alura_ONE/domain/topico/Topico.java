package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;


import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import br.com.alura.Forum_Hub_Alura_ONE.domain.users.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario nomeAutor;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String nomeCurso;

    public Topico(DadosCadastramentoTopico dados, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.nomeCurso = dados.nomeCurso();
        this.status = Status.NÃO_RESPONDIDO;
        this.nomeAutor = autor;
        this.dataCriacao = getDataCriacao();

    }
}
