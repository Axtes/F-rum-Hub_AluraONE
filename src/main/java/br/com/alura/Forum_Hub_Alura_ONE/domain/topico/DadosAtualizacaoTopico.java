package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;

public record DadosAtualizacaoTopico(
        String titulo,
        String nomeCurso,
        String mensagem,
        Status status
) {
}
