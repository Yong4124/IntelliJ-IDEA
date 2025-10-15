package com.du.sec1.util;

import com.du.sec1.entity.User;
import com.du.sec1.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initUsers() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .role("ADMIN")
                    .build();
            
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user1").isEmpty()) {
            User user =  User.builder()
                    .username("user1")
                    .password(passwordEncoder.encode("1234"))
                    .role("USER")
                    .build();

            userRepository.save(user);
        }
    }
}

