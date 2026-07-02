package com.krakedev.proyectos.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.krakedev.proyectos.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtFilter;

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(
	        JwtAuthenticationFilter jwtFilter,
	        UserDetailsService userDetailsService,
	        PasswordEncoder passwordEncoder){

	    this.jwtFilter = jwtFilter;
	    this.userDetailsService = userDetailsService;
	    this.passwordEncoder = passwordEncoder;

	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf->csrf.disable())

                .sessionManagement(session->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth->auth

                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        .anyRequest()
                        .authenticated()

                )
                
                .authenticationProvider(authenticationProvider(
                        userDetailsService,
                        passwordEncoder))
                
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();

    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception{

        return configuration.getAuthenticationManager();

    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder){

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;

    }

}