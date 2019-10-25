package fr.formation.jwtauthserver.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 256, nullable = false, unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "custom_user_role",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles; // Authorities

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonExpired = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonLocked = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean credentialsNonExpired = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean enabled;

    protected CustomUser() {
	// Empty no-arg constructor
    }

    /**
     * Creates a new enabled custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     */
    public CustomUser(String password, String username, Set<Role> roles) {
	this(password, username, roles, true);
    }

    /**
     * Creates a new custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     * @param enabled  {@code true} if enabled; {@code false} otherwise
     */
    public CustomUser(String password, String username, Set<Role> roles,
	    boolean enabled) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
    }

    public Long getId() {
	return id;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public void setAccountNonExpired(boolean value) {
	accountNonExpired = value;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public void setAccountNonLocked(boolean value) {
	accountNonLocked = value;
    }

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean value) {
	credentialsNonExpired = value;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean value) {
	enabled = value;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", password=[PROTECTED], username=" + username
		+ ", roles=" + roles + ", accountNonExpired="
		+ accountNonExpired + ", accountNonLocked=" + accountNonLocked
		+ ", credentialsNonExpired=" + credentialsNonExpired
		+ ", enabled=" + enabled + "}";
    }
}
