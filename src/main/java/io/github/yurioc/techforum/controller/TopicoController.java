package io.github.yurioc.techforum.controller;

import io.github.yurioc.techforum.domain.curso.Curso;
import io.github.yurioc.techforum.domain.curso.CursoRepository;
import io.github.yurioc.techforum.domain.topico.DadosAtualizacaoTopico;
import io.github.yurioc.techforum.domain.topico.DadosCadastroTopico;
import io.github.yurioc.techforum.domain.topico.Topico;
import io.github.yurioc.techforum.domain.topico.TopicoRepository;
import io.github.yurioc.techforum.domain.usuario.Usuario;
import io.github.yurioc.techforum.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroTopico dados){
        if(topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            return ResponseEntity.badRequest().body("Topico ja cadastrado");
        }
        Usuario autor = usuarioRepository.getReferenceById(dados.autorId());
        Curso curso = cursoRepository.getReferenceById(dados.cursoId());

        Topico topico = new Topico(dados, autor, curso);

        topicoRepository.save(topico);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        var topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){
        var optionaltopico = topicoRepository.findById(id);
        if(optionaltopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if(topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            return ResponseEntity.badRequest().body("Dados devem ser diferentes para atualizar");
        }

        var topico = optionaltopico.get();
        topico.atualizarTopico(dados);

        return ResponseEntity.ok(topico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar (@PathVariable Long id) {
        var optionaltopico = topicoRepository.findById(id);
        if(optionaltopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
