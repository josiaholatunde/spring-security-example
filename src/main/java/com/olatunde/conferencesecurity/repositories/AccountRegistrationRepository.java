package com.olatunde.conferencesecurity.repositories;

import com.olatunde.conferencesecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRegistrationRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
