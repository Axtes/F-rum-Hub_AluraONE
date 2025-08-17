package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String mensagem, String titulo, LocalDateTime data, Status status, String nomeAutor, String nomeCurso) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getMensagem(), topico.getTitulo(), topico.getDataCriacao(), topico.getStatus(), topico.getNomeAutor().getNome(), topico.getNomeCurso());
    }
}
