package com.informatorio.myblog.services;

import java.sql.Timestamp;
import java.util.List;

import com.informatorio.myblog.models.UserModel;

public interface UserService {
    
    public void createUser(UserModel userModel);

    public void deleteUser(Long id_user);

    public void updateUser(Long id_user, UserModel userModel);

    public List<UserModel> allUsers();

    public List<UserModel> userForCity();
    
    public List<UserModel> userForDate(Timestamp userTimeStart, Timestamp userTimeEnd);

    public UserModel getUser(Long id);
}
