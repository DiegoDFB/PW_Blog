package br.com.etechoracio.blog.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Post {
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataCriacao;
}
