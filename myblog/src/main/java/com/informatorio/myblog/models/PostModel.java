package com.informatorio.myblog.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Publicacion")
public class PostModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Post")
    private Long id;

    @Column(name = "Titulo", nullable = false)
    @NotBlank
    @NotEmpty
    private String title;

    @Column(name = "Descripcion", nullable = false)
    @NotBlank
    @NotEmpty
    private String description;

    @Column(name = "Contenido", nullable = false)
    @NotBlank
    @NotEmpty
    private String content;

    @Column(name = "Fecha_Creacion", nullable = false)
    private Timestamp createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_Usuario", nullable = false)
    private UserModel author;

    @Column(name = "Publicado", nullable = false)
    @NotNull
    private boolean published;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public UserModel getAuthor() {
        return author;
    }
    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }


    @PrePersist
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public void prePersist() {
        this.createDate = new Timestamp(new Date().getTime());
    }

    @OneToMany(targetEntity = CommentModel.class, mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentModel> comentarios;

    private PostModel() {
        this.comentarios = new ArrayList<CommentModel>();
    }

    private PostModel(Long id, String title, String description, String content, Timestamp createDate, UserModel author, boolean published, List<CommentModel> comentarios) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.createDate = createDate;
        this.author = author;
        this.published = published;
        this.comentarios = comentarios;
    }
    
    public void update(PostModel postModel) {
        if (postModel.getTitle() != null && postModel.getTitle() != this.title) {
            this.title = postModel.getTitle();
        }
        if (postModel.getDescription() != null && postModel.getDescription() != this.description) {
            this.description = postModel.getDescription();
        }
        if (postModel.getContent() != null && postModel.getContent() != this.content) {
            this.content = postModel.getContent();
        }
    }

    private static final long serialVersionUID = 1L;
}