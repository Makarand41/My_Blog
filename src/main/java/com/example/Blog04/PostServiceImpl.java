package com.example.Blog04;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post createPost(Post post) {
        // You can add additional logic/validation before saving the post
        return postRepository.save(post);
    }
    
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public List<Post> getAllPostsForUser(String userEmail) {
        return postRepository.findByEmail(userEmail);
    }

    // Implement other methods for post-related business logic
    @Override
    public void deleteAllPostsForUser(String userEmail) {
        List<Post> userPosts = postRepository.findByEmail(userEmail);
        postRepository.deleteAll(userPosts);
    }
}