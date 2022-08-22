package io.github.pedroermarinho.comandalivreapi.infra.config.security;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UsernameInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.auth.LoadUserByEmail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwtUtil;

    private final LoadUserByEmail loadUserByEmail;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, LoadUserByEmail loadUserByEmail) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.loadUserByEmail = loadUserByEmail;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        var auth = getAuthentication(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String authorizationHeader) {
        var token = authorizationHeader.substring(7);
        if (jwtUtil.isTokenValid(token)) {
            var email = jwtUtil.getUsernameFromToken(token);
            var user = loadUserByEmail.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }

        throw new UsernameInvalidException("Autenticação inválida.");
    }
}
