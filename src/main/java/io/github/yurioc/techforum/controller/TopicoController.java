package io.github.yurioc.techforum.controller;

import io.github.yurioc.techforum.domain.curso.Curso;
import io.github.yurioc.techforum.domain.curso.CursoRepository;
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
    public void cadastrar (@RequestBody @Valid DadosCadastroTopico dados){
        if(topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new RuntimeException("Topico ja cadastrado");
        }
        Usuario autor = usuarioRepository.getReferenceById(dados.autorId());
        Curso curso = cursoRepository.getReferenceById(dados.cursoId());

        Topico topico = new Topico(dados, autor, curso);

        topicoRepository.save(topico);
    }
}
