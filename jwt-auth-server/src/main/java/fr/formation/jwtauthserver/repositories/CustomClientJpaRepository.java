package fr.formation.jwtauthserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.jwtauthserver.entities.CustomClient;

public interface CustomClientJpaRepository
	extends JpaRepository<CustomClient, Long> {

    CustomClient findByClientId(String clientId);
}
