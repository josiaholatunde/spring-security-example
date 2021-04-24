package com.olatunde.conferencesecurity.repositories;

import com.olatunde.conferencesecurity.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<VerificationToken, Long> {

}
