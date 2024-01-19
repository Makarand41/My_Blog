package com.example.Blog04;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PostService postService;


    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getStudent(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        // Check if the email already exists in the database
        Optional<User> existingUser = repository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            // Email already exists, return a conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        } else {
            // Email is unique, proceed with user registration
            User savedUser = repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Find the user by email
        Optional<User> optionalUser = repository.findByEmail(user.getEmail());
        return optionalUser.map(existingUser -> {
            if (existingUser.getPassword().equals(user.getPassword())) {
                // Perform authentication and create a session
              
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found"));
    }
    
    @PostMapping("/{userEmail}/posts")
    public ResponseEntity<Post> createPost(@PathVariable String userEmail, @RequestBody Post post) {
        Optional<User> userOptional = repository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            post.setEmail(user.getEmail()); // Set the email of the post creator
            post.setDate(new Date()); // Set the current date and time
            Post savedPost = postService.createPost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @GetMapping("/{userEmail}/posts")
    public ResponseEntity<List<Post>> getAllPostsForUser(@PathVariable String userEmail) {
        Optional<User> userOptional = repository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            List<Post> userPosts = postService.getAllPostsForUser(userEmail);
            return ResponseEntity.ok(userPosts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

 // Get a specific post for the logged-in user
    @GetMapping("/{userEmail}/posts/{postId}")
    public ResponseEntity<Post> getPostForUser(@PathVariable String userEmail, @PathVariable Long postId) {
        Optional<Post> postOptional = postService.getPostById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            
            // Check if the retrieved post belongs to the specified user
            if (post.getEmail().equals(userEmail)) {
                return ResponseEntity.ok(post);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> credentials) {
        // Extract email and password from the request body
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Find the user by email
        Optional<User> optionalUser = repository.findByEmail(email);

        return optionalUser.map(existingUser -> {
            if (existingUser.getPassword().equals(password)) {
                // Perform authentication and delete the user
                repository.delete(existingUser);
                return ResponseEntity.ok("User deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user"));
    }



    @DeleteMapping("/{userEmail}/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String userEmail, @PathVariable Long postId) {
        Optional<User> userOptional = repository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            Optional<Post> postOptional = postService.getPostById(postId);

            if (postOptional.isPresent()) {
                Post post = postOptional.get();

                // Check if the post belongs to the specified user
                if (post.getEmail().equals(userEmail)) {
                    postService.deletePost(postId);
                    return ResponseEntity.ok("Post deleted successfully!");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to delete this post");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    
    @DeleteMapping("/{userEmail}/posts")
    public ResponseEntity<String> deleteAllPostsForUser(@PathVariable String userEmail) {
        Optional<User> userOptional = repository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            // Call a method in your postService to delete all posts for the user
            postService.deleteAllPostsForUser(userEmail);
            return ResponseEntity.ok("All posts for the user deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
    

    
    
    

    }