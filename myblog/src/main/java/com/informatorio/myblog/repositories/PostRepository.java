package com.informatorio.myblog.repositories;

import java.util.List;

import com.informatorio.myblog.models.PostModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, Long> {
    
    public List<PostModel> findByTitle(String title);
    public List<PostModel> findByPublished();
}
