package com.yunus.web.webyunus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    @Autowired
    private UserServiceJpa userService;

    @Autowired
    private PostService postService;

    @GetMapping("/jpa/users")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUserById(@PathVariable int id) {

        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user id not found :  " + id);
        }

        return user;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        userService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user : " + id);
        }
        userService.delete(user.get());
    }

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> getPostsByUser(@PathVariable int userId) {
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()){
            throw new UserNotFoundException("user : " + userId);
        }
        return user.get().getPostList();
    }

    @PostMapping("/jpa/users/{userId}/post")
    public ResponseEntity<Object> addPostForUser(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> optionalUser = userService.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("user : " + userId);
        }

        post.setUser(optionalUser.get());
        postService.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}

