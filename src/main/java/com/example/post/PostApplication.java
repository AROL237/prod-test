package com.example.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PostApplication {
    private final UserRepo userRepo;

    public PostApplication(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

    @GetMapping
    public User getHello() {
        return userRepo.findById(1L).get();
    }

    @GetMapping("/{s}")
    public String hello(@PathVariable String s) {
        return "hello, " + s;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            userRepo.save(new User(
                    null, "arol", "user", 'M', "arollito1@gamail.com"
            ));
        };
    }
}

@Entity
@Table(name = "user", schema = "prod_db")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private char gender;
    @Column(nullable = false, unique = true)
    private String email;

}

@Repository
interface UserRepo extends JpaRepository<User, Long> {

}