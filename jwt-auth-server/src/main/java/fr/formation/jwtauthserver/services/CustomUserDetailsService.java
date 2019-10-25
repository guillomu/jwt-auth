package fr.formation.jwtauthserver.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.jwtauthserver.dtos.CustomUserCreateDto;

public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Creates and saves a new {@code CustomUser} with given credentials ans
     * default role.
     *
     * @param dto a new user credentials
     * @throws Exception if a {@code CustomUser} already exists with given
     *                   username
     */
    void create(CustomUserCreateDto dto);

    /**
     * Indicates whether or not a {@code CustomUser} already exists with given
     * username.
     *
     * @param username a username
     * @return {@code true} if no {@code CustomUser} already exists with given
     *         username; {@code false} otherwise
     */
    boolean usernameIsUnique(String username);
}
