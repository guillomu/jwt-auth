package fr.formation.jwtauthserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.jwtauthserver.entities.CustomUser;

public interface CustomUserJpaRepository
	extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);
}
