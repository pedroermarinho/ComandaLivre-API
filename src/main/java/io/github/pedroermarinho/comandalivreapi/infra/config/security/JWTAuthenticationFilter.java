package io.github.pedroermarinho.comandalivreapi.infra.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pedroermarinho.comandalivreapi.domain.entities.UserEntity;
import io.github.pedroermarinho.comandalivreapi.domain.exceptions.UserInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.record.CredentialsRecord;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            final CredentialsRecord credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsRecord.class);

            var token = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

            return authenticationManager.authenticate(token);
        } catch (Exception e) {
            throw new UserInvalidException("Usuário ou senha inválidos");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        final String login = ((UserEntity) authResult.getPrincipal()).getEmail();

        final String token = jwtUtil.generateToken(login);

        response.addHeader("Authorization", "Bearer " + token);
    }
}
