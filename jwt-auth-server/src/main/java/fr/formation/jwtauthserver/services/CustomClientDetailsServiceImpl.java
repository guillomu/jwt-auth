package fr.formation.jwtauthserver.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import fr.formation.jwtauthserver.entities.CustomClient;
import fr.formation.jwtauthserver.repositories.CustomClientJpaRepository;

@Service
public class CustomClientDetailsServiceImpl
	implements CustomClientDetailsService {

    private final CustomClientJpaRepository repo;

    protected CustomClientDetailsServiceImpl(CustomClientJpaRepository repo) {
	this.repo = repo;
    }

    @Cacheable("clients")
    @Override
    public ClientDetails loadClientByClientId(String clientId)
	    throws ClientRegistrationException {
	CustomClient customClient = repo.findByClientId(clientId);
	if (customClient == null) {
	    throw new UsernameNotFoundException(
		    "no client found with client_id: " + clientId);
	}
	BaseClientDetails clientDetails = new BaseClientDetails(
		customClient.getClientId(), customClient.getResourceIds(),
		customClient.getScopes(), customClient.getGrantTypes(),
		customClient.getAuthorities(), customClient.getRedirectUris());
	clientDetails.setAccessTokenValiditySeconds(
		customClient.getAccessTokenValiditySeconds());
	clientDetails.setRefreshTokenValiditySeconds(
		customClient.getRefreshTokenValiditySeconds());
	clientDetails.setClientSecret(customClient.getClientSecret());
	return clientDetails;
    }
}
