package com.informatorio.myblog.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Usuario")
public class UserModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false)
    @NotBlank
    @NotEmpty
    private String name;
    
    @Column(name = "Apellido", nullable = false)
    @NotBlank
    @NotEmpty
    private String lastname;

    @Column(name = "Correo", unique = true, nullable = false)
    @NotEmpty
    @Email
    private String email;
    
    @Column(name = "Contraseña", nullable = false)
    @NotBlank
    @NotEmpty
    private String password;

    @Column(name = "Fecha_Creacion")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Timestamp createDate;

    @Column(name = "Ciudad", nullable = false)
    @NotBlank
    @NotEmpty
    private String city;
    
    @Column(name = "Provincia", nullable = false)
    @NotBlank
    @NotEmpty
    private String state;
    
    @Column(name = "Pais", nullable = false)
    @NotBlank
    @NotEmpty
    private String country;


    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostModel> posts;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentModel> comments;

    @PrePersist
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public void prePersist() {
        this.createDate = new Timestamp(new Date().getTime());
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public List<PostModel> getPosts() {
        return posts;
    }
    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    public List<CommentModel> getComments() {
        return comments;
    }
    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public void update(UserModel userModel) {
        if (userModel.getName() != null && userModel.getName() != this.name) {
            this.name = userModel.getName();
        }
        if (userModel.getLastname() != null && userModel.getLastname() != this.lastname) {
            this.lastname = userModel.getLastname();
        }
        if (userModel.getEmail() != null && userModel.getEmail() != this.email) {
            this.email = userModel.getEmail();
        }
        if (userModel.getCity() != null && userModel.getCity() != this.city) {
            this.city = userModel.getCity();
        }
        if (userModel.getState() != null && userModel.getState() != this.state) {
            this.state = userModel.getState();
        }
        if (userModel.getCountry() != null && userModel.getCountry() != this.country) {
            this.country = userModel.getCountry();
        }
    }

    private static final long serialVersionUID = 1L;
}