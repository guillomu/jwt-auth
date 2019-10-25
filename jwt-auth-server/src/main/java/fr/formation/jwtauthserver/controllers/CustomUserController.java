package fr.formation.jwtauthserver.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.jwtauthserver.dtos.CustomUserCreateDto;
import fr.formation.jwtauthserver.services.CustomUserDetailsService;

@RestController
@RequestMapping("/api/users")
public class CustomUserController {

    private final CustomUserDetailsService service;

    protected CustomUserController(CustomUserDetailsService service) {
	this.service = service;
    }

    /**
     * Creates a new user with given username and password, and default role.
     *
     * @param inputs the inputs
     */
    @PostMapping
    protected void create(@RequestBody @Valid CustomUserCreateDto inputs) {
	service.create(inputs);
    }
}
