package com.codedito.service;

import com.codedito.domain.ApplicationUser;
import com.codedito.i18m.Messages;
import com.codedito.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final Messages messages;


    @Autowired

    public CustomUserService(AppUserRepository appUserRepository, Messages messages) {
        this.appUserRepository = appUserRepository;
        this.messages = messages;

    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        ApplicationUser appUser;

        appUser = appUserRepository.findByUsername(userName);
        if (appUser == null) {
            throw new AuthenticationCredentialsNotFoundException(
                    messages.get("loginUserUnknown"));
        } else {
            String pasword2 = appUser.getPassword();
            String password = appUserRepository.findApplicationUserByPassword(appUser.getPassword()).getPassword();
            if (password == pasword2) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                throw new BadCredentialsException(messages.get("loginUserBadCredintials"));

            }
        }
        return appUser;
    }


}