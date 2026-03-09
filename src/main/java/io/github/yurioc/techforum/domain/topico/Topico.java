package io.github.yurioc.techforum.domain.topico;

import io.github.yurioc.techforum.domain.curso.Curso;
import io.github.yurioc.techforum.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "topico")
@Entity(name = "Topico")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;

    private LocalDateTime data_criacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    public Topico (DadosCadastroTopico dados, Usuario autor, Curso curso){
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data_criacao = LocalDateTime.now();
        this.status = StatusTopico.ATIVO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizarTopico(DadosAtualizacaoTopico dados) {
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if(dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if(dados.status() != null) {
            this.status = dados.status();
        }
    }

}
