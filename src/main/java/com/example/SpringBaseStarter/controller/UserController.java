package com.example.SpringBaseStarter.controller;

import com.example.SpringBaseStarter.entity.User;
import com.example.SpringBaseStarter.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.PrivateKey;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepo repo;

    @GetMapping("/public")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }
    @GetMapping("/public/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return ResponseEntity.ok(repo.findById(id).get());
    }
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.ok(repo.saveAndFlush(user));
    }
}
