package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String mensagem, String titulo, String nomeCurso, String nome, LocalDateTime data) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getMensagem(), topico.getTitulo(), topico.getNomeCurso(), topico.getNomeAutor().getNome(),topico.getDataCriacao());
    }
}
