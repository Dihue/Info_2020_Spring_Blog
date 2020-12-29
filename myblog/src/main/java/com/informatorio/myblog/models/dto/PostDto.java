package com.informatorio.myblog.models.dto;

import com.informatorio.myblog.models.PostModel;

public class PostDto {
    
    private Long id_usuario;
    private Long id_post;
    private PostModel post;

    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getId_post() {
        return id_post;
    }
    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public PostModel getPost() {
        return post;
    }
    public void setPost(PostModel post) {
        this.post = post;
    }   
}