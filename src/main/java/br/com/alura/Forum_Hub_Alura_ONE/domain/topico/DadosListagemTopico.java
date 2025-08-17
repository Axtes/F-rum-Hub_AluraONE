package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, String nomeCurso, String nomeAutor, Status status, LocalDateTime dataCriacao) {

    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getNomeCurso(), topico.getNomeAutor().getNome(), topico.getStatus(), topico.getDataCriacao());
    }
}
