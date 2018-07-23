package com.codedito.service;

import com.codedito.domain.ApplicationUser;
import com.codedito.exception.EnabledUserException;
import com.codedito.exception.ExpiredAccountException;
import com.codedito.exception.LockedAccountException;
import com.codedito.i18m.Messages;
import com.codedito.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Messages messages;

    @Autowired

    public UserAuthenticationProvider(AppUserRepository userRepository, PasswordEncoder passwordEncoder, Messages messages) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messages = messages;
    }

    @Override
    public Authentication authenticate(Authentication auth) {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
        ApplicationUser user = userRepository.findByUsername(token.getName());

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException(messages.get("loginUserUnknown"));
        } else {
            if (!checkIfPasswordExists(token, user)) {
                throw new BadCredentialsException(messages.get("loginUserBadCredintials"));
            } else if (!user.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(messages.get("loginUserCredintialsExpired"));
            } else if (!user.isAccountNonExpired()) {
                throw new ExpiredAccountException(messages.get("loginAccountExpired"));
            } else if (!user.isAccountNonLocked()) {
                throw new LockedAccountException(messages.get("loginUserlocked"));
            } else if (!user.isEnabled()) {
                throw new EnabledUserException(messages.get("loginUserDisabled"));
            }

        }
        return new

                UsernamePasswordAuthenticationToken(user, user.getPassword(), user.

                getAuthorities());
    }


    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean checkIfPasswordExists(UsernamePasswordAuthenticationToken token, ApplicationUser user) {
        return passwordEncoder.matches(token.getCredentials().toString(), user.getPassword());
    }

}