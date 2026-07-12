package com.Sprout.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Provides a single, shared BCryptPasswordEncoder bean used anywhere
 * we hash or verify a Farmer/Worker/Admin password.
 *
 * Note: this only pulls in spring-security-crypto (just the hashing
 * utilities), not full spring-boot-starter-security, so it does NOT
 * add a login wall or filter chain in front of your existing routes.
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}