package br.com.etechoracio.blog.controller;

import br.com.etechoracio.blog.entity.Post;
import br.com.etechoracio.blog.repository.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository repository;

    @GetMapping
    public List<Post> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Post> inserir(@RequestBody Post post){
        post.setDataCriacao(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

}
