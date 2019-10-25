package fr.formation.jwtauthserver.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.jwtauthserver.dtos.CustomUserCreateDto;
import fr.formation.jwtauthserver.entities.CustomUser;
import fr.formation.jwtauthserver.entities.Role;
import fr.formation.jwtauthserver.repositories.CustomUserJpaRepository;
import fr.formation.jwtauthserver.repositories.RoleJpaRepository;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final CustomUserJpaRepository repo;

    private final RoleJpaRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    protected CustomUserDetailsServiceImpl(CustomUserJpaRepository repo,
	    RoleJpaRepository roleRepo, PasswordEncoder passwordEncoder) {
	this.repo = repo;
	this.roleRepo = roleRepo;
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	CustomUser customUser = repo.findByUsername(username);
	if (customUser == null) {
	    throw new UsernameNotFoundException(
		    "no user found with username: " + username);
	}
	Set<GrantedAuthority> authorities = buildAuthorities(
		customUser.getRoles());
	UserDetails userDetails = new User(customUser.getUsername(),
		customUser.getPassword(), customUser.isEnabled(),
		customUser.isAccountNonExpired(),
		customUser.isCredentialsNonExpired(),
		customUser.isAccountNonLocked(), authorities);
	return userDetails;
    }

    private static Set<GrantedAuthority> buildAuthorities(Set<Role> roles) {
	return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode()))
		.collect(Collectors.toSet());
    }

    @Override
    public void create(CustomUserCreateDto dto) {
	String encoded = passwordEncoder.encode(dto.getPassword());
	Role role = roleRepo.findByDefaultRoleTrue();
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	CustomUser customUser = new CustomUser(encoded, dto.getUsername(),
		roles);
	repo.save(customUser);
    }

    @Override
    public boolean usernameIsUnique(String username) {
	return !repo.existsByUsernameIgnoreCase(username);
    }
}
