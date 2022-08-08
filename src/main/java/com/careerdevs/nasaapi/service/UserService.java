package com.careerdevs.nasaapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.careerdevs.nasaapi.models.User;
import com.careerdevs.nasaapi.repos.UserRepo;
import com.careerdevs.nasaapi.security.services.UserDetailsImpl;

public class UserService {
    
    @Autowired
    UserRepo userRepo;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) auth.getPrincipal();

        Optional<User> currentUser = userRepo.findById(userDetailsImpl.getId());

        if (currentUser.isEmpty()) {
            return null;
        }

        return currentUser.get();

    }

}
