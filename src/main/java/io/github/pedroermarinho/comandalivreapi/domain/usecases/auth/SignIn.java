package io.github.pedroermarinho.comandalivreapi.domain.usecases.auth;

import io.github.pedroermarinho.comandalivreapi.domain.record.AuthRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.CredentialsRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SignIn {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    private final SearchUser searchUser;

    public SignIn(AuthenticationManager authenticationManager, JWTUtil jwtUtil, SearchUser searchUser) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.searchUser = searchUser;
    }

    public AuthRecord execute(CredentialsRecord credentials) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = searchUser.searchUserByEmail(credentials.getEmail());

        return new AuthRecord(
                jwtUtil.generateToken(credentials.getEmail()),
                user
        );
    }

}
