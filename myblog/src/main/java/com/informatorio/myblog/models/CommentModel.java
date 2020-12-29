package com.informatorio.myblog.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Comentario")
public class CommentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Comentario", length = 200)
    @NotEmpty
    @NotBlank
    private String comment;

    @Column(name = "Fecha_Creacion")
    private Timestamp createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_Usuario", nullable = false)
    private UserModel user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_Post", nullable = false)
    private PostModel post;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }

    public PostModel getPost() {
        return post;
    }
    public void setPost(PostModel post) {
        this.post = post;
    }

    public void update(CommentModel commentModel) {
        if (commentModel.getComment() != null && commentModel.getComment() != this.comment) {
            this.comment = commentModel.getComment();
        }
    }
    
    @PrePersist
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public void prePersist() {
        this.createDate = new Timestamp(new Date().getTime());
    }
    
    private static final long serialVersionUID = 1L;
}
