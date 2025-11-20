package com.example.LoginProject.service;

import com.example.LoginProject.entity.User;
import com.example.LoginProject.exception.EmptyListException;
import com.example.LoginProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {

        try {

            List<User> users = userRepository.findAll();

            if(users.isEmpty()){
                throw new EmptyListException("No users found");
            }

            return users;

        }catch(EmptyListException e){
            throw e;
        }
        catch (Exception e){
            log.error("Error with getting all users");
            throw e;
        }

    }

}
