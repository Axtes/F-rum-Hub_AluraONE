package br.com.alura.Forum_Hub_Alura_ONE.controller;


import br.com.alura.Forum_Hub_Alura_ONE.domain.topico.*;
import br.com.alura.Forum_Hub_Alura_ONE.domain.users.Usuario;
import br.com.alura.Forum_Hub_Alura_ONE.domain.users.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastramentoTopico dados, UriComponentsBuilder uriBuIlder) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var topico = new Topico(dados, usuarioLogado);
        topicoRepository.save(topico);

        var uri = uriBuIlder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault()Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {

            var topico = topicoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

            var authentication = SecurityContextHolder.getContext().getAuthentication();
            String usuarioLogado = authentication.getName();

            if (!topico.getNomeAutor().getEmail().equals(usuarioLogado)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não pode atualizar este tópico!");
            }

            if (dados.titulo() != null) {
                topico.setTitulo(dados.titulo());
            }
            if (dados.mensagem() != null) {
                topico.setMensagem(dados.mensagem());
            }
            if (dados.nomeCurso() != null) {
                topico.setNomeCurso(dados.nomeCurso());
            }
            if (dados.status() != null) {
                topico.setStatus(dados.status());
            }
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLogado = authentication.getName();

        if (!topico.getNomeAutor().getEmail().equals(usuarioLogado)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não pode excluir este tópico!");
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
