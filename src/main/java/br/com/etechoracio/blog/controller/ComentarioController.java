package br.com.etechoracio.blog.controller;

import br.com.etechoracio.blog.entity.Comentario;
import br.com.etechoracio.blog.entity.Post;
import br.com.etechoracio.blog.repository.ComentarioRepository;
import br.com.etechoracio.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Diego Francischette Blanco e Arthur de Jesus Lima     3BI

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioRepository repository;

    @GetMapping
    public List<Comentario> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Comentario> inserir(@RequestBody Comentario comentario){
        comentario.setDataCriacao(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentario));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void atualizar(@RequestBody Comentario comentario, @PathVariable Long id) {
        comentario.setId(buscarPorId(id).getBody().getId());
        repository.save(comentario);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable Long id){

        repository.deleteById(id);

    }

}
