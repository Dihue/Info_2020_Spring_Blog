package com.informatorio.myblog.models.dto;

import com.informatorio.myblog.models.UserModel;

public class UserDto {
    
    private Long id;
    private UserModel usuario;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUsuario() {
        return usuario;
    }
    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }   
}