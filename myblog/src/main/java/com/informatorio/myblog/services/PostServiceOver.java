package com.informatorio.myblog.services;

import java.util.List;

import com.informatorio.myblog.models.PostModel;
import com.informatorio.myblog.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceOver implements PostService {

    @Autowired
    public PostRepository postRepository;

    @Override
    public PostModel getPost(Long id_post) {
        return postRepository.getOne(id_post);
    }

    @Override
    public PostModel createPost(PostModel postModel) {
        return postRepository.save(postModel);
    }

    @Override
    public boolean deletePost(Long id_post) {
        PostModel post = postRepository.getOne(id_post);
        if (post != null) {
            postRepository.deleteById(id_post);
            return true;
        }
        return false;
    }

    @Override
    public PostModel updatePost(Long id_post, PostModel postModel) {
        PostModel postUpdate = postRepository.getOne(id_post);
        postUpdate.update(postModel);
        return postRepository.save(postUpdate);
    }

    @Override
    public List<PostModel> allPost() {
        return postRepository.findAll();
    }

    @Override
    public List<PostModel> searchForTitle(String titulo) {
        return postRepository.findByTitle(titulo);
    }
}