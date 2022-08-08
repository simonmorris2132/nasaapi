package com.careerdevs.nasaapi.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.careerdevs.nasaapi.models.User;
import com.careerdevs.nasaapi.repos.UserRepo;

@Service
public class UserDetailServiceImple implements UserDetailsService {
    
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));

        return (UserDetails) UserDetailsImpl.build(user);
    }


}