package com.informatorio.myblog.services;

import java.util.List;

import com.informatorio.myblog.models.CommentModel;

public interface CommentService {
    
    public CommentModel getComment(Long id_comment);

    public CommentModel createComment(CommentModel commentModel);

    public boolean deleteComment(Long id_comment);

    public CommentModel updateComment(Long id_comment, CommentModel commentModel);

    public List<CommentModel> searchComment(Long id_comment, Integer quantity);
}