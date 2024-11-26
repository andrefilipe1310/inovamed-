package com.inovamed.clinical_study_system.security;


import com.inovamed.clinical_study_system.exception.UserNotFoundException;
import com.inovamed.clinical_study_system.repository.UserRepository;
import com.inovamed.clinical_study_system.service.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException, UserNotFoundException {
        // Permitir acesso ao endpoint de login sem autenticação
        if (request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);


        if(token != null){
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(login);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }




        filterChain.doFilter(request, response);
    }



    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ","");
    }
}
