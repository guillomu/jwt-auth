package fr.formation.jwtauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	// Provides an AuthenticationManager as Spring Bean
	return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	// Disable CSRF, no need with JWT if not cookie-based
	http.csrf().disable()
		// Disable sessions for statelessness
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// Authorize /api/users for anonymous users on POST only
		.and().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/users").anonymous()
		// Deny all other accesses
		.anyRequest().denyAll();
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/oauth/token");
    }

    /**
     * Provides a password encoder to Spring for decoding. Also needed for user
     * creation to encode passwords.
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}
