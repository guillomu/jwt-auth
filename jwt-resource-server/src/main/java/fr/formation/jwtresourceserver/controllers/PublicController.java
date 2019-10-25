package fr.formation.jwtresourceserver.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    protected String welcome(Authentication authentication) {
	return "Public welcome! Authenticated=" + (authentication != null);
    }

    @GetMapping("/noInfo")
    protected String noInfo() {
	return "Public welcome!";
    }
}
