package com.example.LoginProject.service;

import com.example.LoginProject.entity.User;
import com.example.LoginProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

}
