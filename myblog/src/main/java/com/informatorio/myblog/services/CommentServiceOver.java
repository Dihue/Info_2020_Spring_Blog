package com.informatorio.myblog.services;

import java.util.List;

import com.informatorio.myblog.models.CommentModel;
import com.informatorio.myblog.repositories.CommentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceOver implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentModel getComment(Long id_comment) {
        return commentRepository.getOne(id_comment);
    }

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        return commentRepository.save(commentModel);
    }

    @Override
    public boolean deleteComment(Long id_comment) {
        CommentModel comentario = commentRepository.getOne(id_comment);
        if (comentario != null) {
            commentRepository.deleteById(id_comment);
            return true;
        }
        return false;
    }

    @Override
    public CommentModel updateComment(Long id_comment, CommentModel commentModel) {
        CommentModel commentUpdate = commentRepository.getOne(id_comment);
        commentUpdate.update(commentModel);
        return commentRepository.save(commentUpdate);
    }

    private Logger log = LoggerFactory.getLogger(CommentServiceOver.class);

    @Override
    public List<CommentModel> searchComment(Long id_comment, Integer quantity) {
        List<CommentModel> comentarios = commentRepository.commentPost(id_comment, quantity);
        log.info("Comentario: "+ comentarios.toString());
        return comentarios;
    }   
}