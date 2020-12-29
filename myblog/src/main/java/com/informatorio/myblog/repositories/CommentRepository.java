package com.informatorio.myblog.repositories;

import java.util.List;

import com.informatorio.myblog.models.CommentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    
    @Query(value = "SELECT * FROM comentario WHERE id_post =? ORDER BY fecha_creacion DESC LIMIT ?", nativeQuery = true)
    public List<CommentModel> commentPost(Long post, Integer quantity);
}
