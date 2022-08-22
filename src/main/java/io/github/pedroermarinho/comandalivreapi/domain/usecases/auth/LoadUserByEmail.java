package io.github.pedroermarinho.comandalivreapi.domain.usecases.auth;

import io.github.pedroermarinho.comandalivreapi.domain.exceptions.EmailInvalidException;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoadUserByEmail implements UserDetailsService {

    private final SearchUser searchUser;

    public LoadUserByEmail(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            throw new EmailInvalidException("O e-mail não pode ser nulo.");
        }

        var user = searchUser.searchUserByEmail(email);

        return user.toEntity();
    }
}

