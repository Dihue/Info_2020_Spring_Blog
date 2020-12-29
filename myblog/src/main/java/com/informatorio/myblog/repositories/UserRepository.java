package com.informatorio.myblog.repositories;

import java.util.Date;
import java.util.List;

import com.informatorio.myblog.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    public List<UserModel> findByCity(String city);

    public List<UserModel> findByCreateDate(Date userTimeStart, Date userTimeEnd);
}
