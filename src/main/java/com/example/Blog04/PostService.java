package com.example.Blog04;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post post);
    // Other methods for post-related business logic
    void deletePost(Long id);
    

    void deleteAllPostsForUser(String userEmail);
    List<Post> getAllPostsForUser(String userEmail);
}
