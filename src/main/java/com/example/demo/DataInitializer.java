package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public org.springframework.boot.CommandLineRunner init(UserRepository repo){
        return args -> {

            if(repo.findByEmail("admin@college.com").isEmpty()){

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@college.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");

                repo.save(admin);

                System.out.println("Admin user created!");
            }
        };
    }
}