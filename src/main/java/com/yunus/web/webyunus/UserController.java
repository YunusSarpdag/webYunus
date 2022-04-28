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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getUserList();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){

        User user = userService.getUserById(id);
        if(user == null){
            throw new UserNotFoundException("user id not found :  " + id);
        }

        return user;
    }

    @PostMapping("users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
        Optional<Integer> maxId = userService.getUserList().stream().map(u-> u.getId()).max((o1, o2) -> o1-o2);
        user.setId(maxId.get() + 1);
        userService.addUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User user = userService.deleteUserById(id);
        if(user == null){
            throw new UserNotFoundException("user : " + id );
        }
    }
}
