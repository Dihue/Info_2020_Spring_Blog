package com.informatorio.myblog.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.informatorio.myblog.models.UserModel;
import com.informatorio.myblog.models.dto.UserDto;
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
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserServiceOver usuarioService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserModel userModel) {

        usuarioService.createUser(userModel);
        return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {

        usuarioService.updateUser(userDto.getId(), userDto.getUsuario());
        return new ResponseEntity<>("Usuario actualizado", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {

        usuarioService.deleteUser(userDto.getId());
        return new ResponseEntity<>("Usuario Eliminado", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allinUser() {
        
        List<UserModel> usuarios = usuarioService.allUsers();
        if (usuarios.size()==0) {
            return new ResponseEntity<>("No hay usuarios", HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/fecha_{fecha}")
    public ResponseEntity<?> userDate(@PathVariable String fecha) {
        
        Timestamp ahora = new Timestamp(new Date().getTime());
        Timestamp fechaParam = Timestamp.valueOf(fecha + "00:00:00.000000000");

        List<UserModel> usuarios = usuarioService.userForDate(fechaParam, ahora);
        if (usuarios.size()>0) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontro usuarios", HttpStatus.OK);
    }

    @GetMapping("/resistencia")
    public ResponseEntity<?> userForResistencia() {
        return new ResponseEntity<>(usuarioService.userForCity(), HttpStatus.OK);
    }
}