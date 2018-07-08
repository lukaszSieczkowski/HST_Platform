package com.codedito.service;


import com.codedito.domain.ApplicationUser;
import com.codedito.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    AppUserRepository appUserRepository;

    @Autowired
    public CustomUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return appUserRepository.findByUsername(userName);
    }
}
