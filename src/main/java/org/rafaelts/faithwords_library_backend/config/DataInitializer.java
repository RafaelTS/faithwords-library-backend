package org.rafaelts.faithwords_library_backend.config;

import org.rafaelts.faithwords_library_backend.domain.model.User;
import org.rafaelts.faithwords_library_backend.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("{noop}admin123"); // {noop} para senha sem criptografia, só pra POC
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Admin user created!");
            }
        };
    }
}
