package com.example.demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser){

        Optional<User> user = userRepository.findByEmail(loginUser.getEmail());

        if(user.isPresent() &&
           user.get().getPassword().equals(loginUser.getPassword())){

            return user.get();   // ✅ now valid
        }

        return null;
    }
}