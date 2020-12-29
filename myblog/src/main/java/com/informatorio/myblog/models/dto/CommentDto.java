package com.informatorio.myblog.models.dto;

public class CommentDto {
    
    private Long autor;
    private Long post;
    private Long comment;
    private String commentString;
    private Integer quantity;

    public Long getAutor() {
        return autor;
    }
    public void setAutor(Long autor) {
        this.autor = autor;
    }

    public Long getPost() {
        return post;
    }
    public void setPost(Long post) {
        this.post = post;
    }

    public Long getComment() {
        return comment;
    }
    public void setComment(Long comment) {
        this.comment = comment;
    }

    public String getCommentString() {
        return commentString;
    }
    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }   
}