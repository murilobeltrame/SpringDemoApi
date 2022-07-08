package com.murilobeltrame.apidemo.controllers;

import com.murilobeltrame.apidemo.models.User;
import com.murilobeltrame.apidemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAllUsers(){ return userRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value="id") long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) return ResponseEntity.ok().body(user.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Validated @RequestBody User request){
        var user = userRepository.save(request);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable(value="id") long id, @Validated @RequestBody User request) {
        if (id != request.getId()) return ResponseEntity.badRequest().build();
        var user = userRepository.findById(id);
        if (!user.isPresent()) return ResponseEntity.notFound().build();
        userRepository.save(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(value="id") long id) {
        var user = userRepository.findById(id);
        if (!user.isPresent()) return ResponseEntity.notFound().build();
        userRepository.delete(user.get());
        return ResponseEntity.noContent().build();
    }
}
