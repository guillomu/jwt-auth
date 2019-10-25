package fr.formation.jwtauthserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false, unique = true)
    private String clientId;

    @Column(length = 256, nullable = false)
    private String clientSecret;

    @Column(length = 256)
    private String resourceIds;

    @Column(length = 256)
    private String scopes;

    @Column(length = 256)
    private String grantTypes;

    @Column(length = 256)
    private String authorities;

    @Column(length = 256)
    private String redirectUris;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    protected CustomClient() {
	// Empty no-arg constructor
    }

    public Long getId() {
	return id;
    }

    public String getClientId() {
	return clientId;
    }

    public String getClientSecret() {
	return clientSecret;
    }

    public String getResourceIds() {
	return resourceIds;
    }

    public String getScopes() {
	return scopes;
    }

    public String getGrantTypes() {
	return grantTypes;
    }

    public String getAuthorities() {
	return authorities;
    }

    public String getRedirectUris() {
	return redirectUris;
    }

    public Integer getAccessTokenValiditySeconds() {
	return accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
	return refreshTokenValiditySeconds;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", clientId=" + clientId
		+ ", clientSecret=[PROTECTED]}";
    }
}
