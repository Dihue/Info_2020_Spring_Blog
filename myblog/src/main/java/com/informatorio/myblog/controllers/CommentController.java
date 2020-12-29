package com.informatorio.myblog.controllers;

import java.util.List;

import com.informatorio.myblog.models.CommentModel;
import com.informatorio.myblog.models.PostModel;
import com.informatorio.myblog.models.UserModel;
import com.informatorio.myblog.models.dto.CommentDto;
import com.informatorio.myblog.services.CommentServiceOver;
import com.informatorio.myblog.services.PostServiceOver;
import com.informatorio.myblog.services.UserServiceOver;

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
@RequestMapping("/api/v1/comment")
public class CommentController {
    
    @Autowired
    private UserServiceOver userService;

    @Autowired
    private PostServiceOver postService;

    @Autowired
    private CommentServiceOver commentService;

    @PostMapping("/")
    public ResponseEntity<?> createdComment(@RequestBody CommentDto commentDto) {
        Long id_autor = commentDto.getAutor();
        Long id_post = commentDto.getPost();
        CommentModel comentario = new CommentModel();

        comentario.setComment(commentDto.getCommentString());
        
        UserModel autor = userService.getUser(id_autor);
        PostModel post = postService.getPost(id_post);

        comentario.setUser(autor);
        comentario.setPost(post);
        commentService.createComment(comentario);

        return new ResponseEntity<>("Comentario guardado", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto) {

        Long id_comment = commentDto.getComment();
        CommentModel commentModel = commentService.getComment(id_comment);

        if (commentDto.getCommentString() != "" && commentModel != null) {
            commentModel.setComment(commentDto.getCommentString());
            if (commentService.updateComment(id_comment, commentModel) != null) {
                return new ResponseEntity<>("Comentario actualizado", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Error al actualizar", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteComment(@RequestBody CommentDto commentDto) {

        if (commentService.deleteComment(commentDto.getComment())) {
            return new ResponseEntity<>("Comentario eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al eliminar", HttpStatus.OK);
    }

    @GetMapping("/{id_comment}-{quantity}")
    public ResponseEntity<?> getComment(@PathVariable Long id_comment, @PathVariable Integer quantity) {

        List<CommentModel> comentarios = commentService.searchComment(id_comment, quantity);
        return new ResponseEntity<>(comentarios,HttpStatus.OK);
    }
}