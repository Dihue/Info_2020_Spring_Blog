package com.informatorio.myblog.controllers;

import java.util.List;

import com.informatorio.myblog.models.PostModel;
import com.informatorio.myblog.models.UserModel;
import com.informatorio.myblog.models.dto.PostDto;
import com.informatorio.myblog.services.PostServiceOver;
import com.informatorio.myblog.services.UserServiceOver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    private Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostServiceOver postService;

    @Autowired
    private UserServiceOver userService;

    @PostMapping("/")
    public ResponseEntity<?> createPost(@RequestBody PostDto wrapper) {

        UserModel autor = userService.getUser(wrapper.getId_usuario());
        PostModel post = wrapper.getPost();
        post.setAuthor(autor);

        if (autor != null) {
            if (postService.createPost(post) != null) {
                return new ResponseEntity<>("Publicacion creada", HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Error al crear", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deletePost(@RequestBody PostDto postDto) {

        if (postService.deletePost(postDto.getId_post())) {
            return new ResponseEntity<>("Publicacion eliminada", HttpStatus.OK);
        }       
        return new ResponseEntity<>("Error al eliminar", HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto) {

        if (postService.updatePost(postDto.getId_post(), postDto.getPost()) != null) {
            return new ResponseEntity<>("Publicacion actualizada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar", HttpStatus.OK);
    }

    @GetMapping("/allpost")
    public ResponseEntity<?> allinPost() {
        
        List<PostModel> posts = postService.allPost();
        if (posts.size()>0) {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay publicaciones", HttpStatus.OK);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<?> postForTitle(@PathVariable String titulo) {

        List<PostModel> posts = postService.searchForTitle(titulo);
        if (posts.size()>0) {
            log.info("Tamaño: "+ posts.size());
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}