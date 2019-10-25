package fr.formation.jwtresourceserver.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    protected String welcomeAnyRole() {
	return "@PreAuthorize(\"hasAnyRole('USER', 'ADMIN')\") access welcome!";
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') and hasRole('ADMIN')")
    protected Authentication me(Authentication authentication) {
	return authentication;
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    protected String roleUserOnly() {
	return "@Secured(\"ROLE_USER\") only access!";
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    protected String roleAdminOnly() {
	return "@Secured(\"ROLE_ADMIN\") only access!";
    }
}
