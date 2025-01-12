package com.biologix.v1.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.biologix.v1.entities.User;
import com.biologix.v1.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User register(User u)
    {
        return userRepo.save(u);
    }

    public Boolean login( String email, @RequestParam("password") String password)
    {
        User u = userRepo.findUserByEmail(email).get();
        if(u.getPassword().equals(password))
        {
            return true;
        }
        return false;
    }

    public User userInfo(String email)
    {
        return userRepo.findUserByEmail(email).orElse(null);
    }
    
}
