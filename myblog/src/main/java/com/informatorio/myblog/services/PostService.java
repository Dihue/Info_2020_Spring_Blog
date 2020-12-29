package com.informatorio.myblog.services;

import java.util.List;

import com.informatorio.myblog.models.PostModel;

public interface PostService {
    
    public PostModel getPost(Long id_post);

    public PostModel createPost(PostModel postModel);

    public boolean deletePost(Long id_post);

    public PostModel updatePost(Long id_post, PostModel postModel);

    public List<PostModel> allPost();

    public List<PostModel> searchForTitle(String titulo);
}
