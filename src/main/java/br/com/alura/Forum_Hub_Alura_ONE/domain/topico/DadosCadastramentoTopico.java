package br.com.alura.Forum_Hub_Alura_ONE.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastramentoTopico(
        @NotBlank
        String mensagem,
        @NotBlank
        String nomeCurso,
        @NotBlank
        String titulo
) {
        public DadosCadastramentoTopico(Topico topico) {
                this(topico.getMensagem(), topico.getNomeCurso(), topico.getTitulo());
        }
}
