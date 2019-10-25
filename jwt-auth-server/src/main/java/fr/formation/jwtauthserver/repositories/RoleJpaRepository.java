package fr.formation.jwtauthserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.jwtauthserver.entities.Role;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    Role findByDefaultRoleTrue();
}
