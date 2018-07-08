package com.codedito.repository;

import com.codedito.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String login);
}
