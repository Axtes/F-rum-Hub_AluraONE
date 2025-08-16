package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;


import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario nomeAutor;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String nomeCurso;

    public Topico(DadosCadastramentoTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.nomeCurso = dados.nomeCurso();
        this.status = status.NÃO_RESPONDIDO;
        this.nomeAutor = nomeAutor;
        this.dataCriacao = getDataCriacao();

    }
}
