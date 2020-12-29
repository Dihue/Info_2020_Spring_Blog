package com.informatorio.myblog.services;

import java.sql.Timestamp;
import java.util.List;

import com.informatorio.myblog.models.UserModel;
import com.informatorio.myblog.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceOver implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Override
    public void deleteUser(Long id_user) {
        userRepository.deleteById(id_user);
    }

    @Override
    public void updateUser(Long id_user, UserModel userModel) {
        UserModel userUpdate;
        userUpdate = userRepository.getOne(id_user);
        userUpdate.update(userModel);
        userRepository.save(userUpdate);
    }

    @Override
    public List<UserModel> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserModel> userForCity() {
        String city = "Resistencia";
        return userRepository.findByCity(city);
    }

    @Override
    public List<UserModel> userForDate(Timestamp userTimeStart, Timestamp userTimeEnd) {
        return userRepository.findByCreateDate(userTimeStart, userTimeEnd);
    }

    @Override
    public UserModel getUser(Long id) {
        return userRepository.getOne(id);
    }
}
