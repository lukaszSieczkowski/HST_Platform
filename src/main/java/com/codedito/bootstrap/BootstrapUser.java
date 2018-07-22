package com.codedito.bootstrap;

import com.codedito.domain.ApplicationUser;
import com.codedito.domain.Authority;
import com.codedito.domain.Role;
import com.codedito.repository.AppUserRepository;
import com.google.common.collect.Sets;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootstrapUser implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BootstrapUser(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        ApplicationUser user = ApplicationUser.builder()
                .username("user")
                .password(passwordEncoder.encode("pass"))
                .email("test@mail.com")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(false)
                .enabled(true)
                .authorities(Sets.newHashSet(new Authority(Role.USER))).build();

        ApplicationUser admin = ApplicationUser.builder()
                .username("admin")
                .password(passwordEncoder.encode("pass"))
                .email("test2@mail.com")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        ApplicationUser userWitchExpriredCredentials = ApplicationUser.builder()
                .username("expiredCredentials")
                .password(passwordEncoder.encode("pass"))
                .email("test3@mail.com")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(false)
                .enabled(true)
                .authorities(Sets.newHashSet(new Authority(Role.ADMIN))).build();

        appUserRepository.save(user);
        appUserRepository.save(admin);
        appUserRepository.save(userWitchExpriredCredentials);
    }
}
