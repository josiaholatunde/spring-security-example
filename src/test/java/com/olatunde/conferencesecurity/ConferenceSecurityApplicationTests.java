package com.olatunde.conferencesecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
class ConferenceSecurityApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		log.info("Encoded password -> {}", passwordEncoder.encode("password"));
	}

}
