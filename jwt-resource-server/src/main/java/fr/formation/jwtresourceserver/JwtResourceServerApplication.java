package fr.formation.jwtresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class JwtResourceServerApplication {

    public static void main(String[] args) {
	SpringApplication.run(JwtResourceServerApplication.class, args);
    }
}
