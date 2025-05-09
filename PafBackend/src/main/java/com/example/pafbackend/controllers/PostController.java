package com.example.pafbackend.controllers;

import com.example.pafbackend.models.Post;
import com.example.pafbackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController //return data
@RequestMapping("/api/posts") //base URL or base path
public class PostController {

    //autowired repository and inject constructor
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //API endpoints
    // GET: Retrieve all Post with User details populated
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // GET: Retrieve a Post by ID with User details populated
    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK); //HttpStatus.OK 200 - success
    }

    // POST: Create a new Post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED); //HttpStatus.CREATED 201 - response
    }

    // DELETE: Delete a Post by ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId) {
        postRepository.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //HttpStatus.NO_CONTENT 204 - send status without response body
    }

    // // PUT: Update a Post by ID
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @RequestBody Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            // Update fields if present in the request body
            if (updatedPost.getMediaLink() != null) {
                existingPost.setMediaLink(updatedPost.getMediaLink());
            }
            if (updatedPost.getMediaType() != null) {
                existingPost.setMediaType(updatedPost.getMediaType());
            }
            if (updatedPost.getContentDescription() != null) {
                existingPost.setContentDescription(updatedPost.getContentDescription());
            }
            // Save the updated post
            Post savedPost = postRepository.save(existingPost);
            return new ResponseEntity<>(savedPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //HttpStatus.OK 200 - success
        }
    }
}
