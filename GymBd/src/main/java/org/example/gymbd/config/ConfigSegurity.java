package org.example.gymbd.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ConfigSegurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    @Bean
    public UserDetailsService userDetailsService(UserDao userDao) {
        return username -> {
            Optional<com.company.bitacora.backend.model.User> userOptional = userDao.findByUsername(username);
            com.company.bitacora.backend.model.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            Set<GrantedAuthority> authorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        };
}
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(configure -> configure

    )
    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws
    Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance(); // Contraseñas sin encriptar
    }

}