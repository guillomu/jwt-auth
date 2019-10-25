package fr.formation.jwtauthserver.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import fr.formation.jwtauthserver.validation.UniqueUsername;

public class CustomUserCreateDto {

    @NotBlank(message = "{E_NOT_BLANK}")
    @Length(max = 256, message = "{E_LENGTH}")
    @UniqueUsername(message = "{E_NOT_UNIQUE}")
    private String username;

    @NotBlank(message = "{E_NOT_BLANK}")
    @Length(min = 8, max = 20, message = "{E_LENGTH}")
    private String password;

    protected CustomUserCreateDto() {
	// Empty no-arg constructor
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "{username=" + username + ", password=[PROTECTED]}";
    }
}
