package fr.formation.jwtauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import fr.formation.jwtauthserver.services.CustomClientDetailsService;
import fr.formation.jwtauthserver.services.CustomUserDetailsService;

/**
 * Configuration class to deal with the authorization server configuration.
 */
@Configuration
@EnableAuthorizationServer
public class JwtAuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;

    private final CustomClientDetailsService clientDetailsService;

    private final CustomUserDetailsService userDetailsService;

    protected JwtAuthServerConfig(
	    AuthenticationManager authenticationManagerBean,
	    CustomClientDetailsService clientDetailsService,
	    CustomUserDetailsService userDetailsService) {
	this.authenticationManagerBean = authenticationManagerBean;
	this.clientDetailsService = clientDetailsService;
	this.userDetailsService = userDetailsService;
    }

    /**
     * Token store, but not really a store, can read and write JWT.
     */
    @Bean
    public TokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * A token converter for JWT and specifies a signing key.
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	converter.setSigningKey("my-symmetric-key"); // symmetric key
	return converter;
    }

    /**
     * Token management.
     */
    @Bean
    public DefaultTokenServices tokenServices() {
	DefaultTokenServices services = new DefaultTokenServices();
	services.setTokenStore(tokenStore());
	services.setSupportRefreshToken(true); // If required
	return services;
    }

    /**
     * All in one.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer)
	    throws Exception {
	configurer.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.authenticationManager(authenticationManagerBean)
		// Configure with custom user details service
		.userDetailsService(userDetailsService);
    }

    /**
     * Change authorization server security. More likely specific endpoints.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer configurer)
	    throws Exception {
	// To decode the access token, denied by default
	// Indicate that an authenticated client can check tokens
	configurer.checkTokenAccess("isAuthenticated()");
    }

    /**
     * Configure clients, where clients are stored and how to encode/decode
     * passwords.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer)
	    throws Exception {
	// Configure with custom client details service
	configurer.withClientDetails(clientDetailsService);
    }
}
