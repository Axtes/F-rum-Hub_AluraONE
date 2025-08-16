package br.com.alura.Forum_Hub_Alura_ONE.controller;


import br.com.alura.Forum_Hub_Alura_ONE.domain.topico.DadosCadastramentoTopico;
import br.com.alura.Forum_Hub_Alura_ONE.domain.topico.DadosDetalhamentoTopico;
import br.com.alura.Forum_Hub_Alura_ONE.domain.topico.Topico;
import br.com.alura.Forum_Hub_Alura_ONE.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(DadosCadastramentoTopico dados, UriComponentsBuilder uriBuIlder) {
        var topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuIlder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
}
