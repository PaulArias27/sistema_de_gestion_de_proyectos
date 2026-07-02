package com.krakedev.proyectos.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenBlacklistService blacklist;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request,response);
            return;

        }

        String token = authHeader.substring(7);

        if(blacklist.estaInvalidado(token)){

            filterChain.doFilter(request,response);
            return;

        }

        String username = jwtService.obtenerUsername(token);

        if(username!=null &&
                SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails user =
                    userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request));

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        }

        filterChain.doFilter(request,response);

    }

}
