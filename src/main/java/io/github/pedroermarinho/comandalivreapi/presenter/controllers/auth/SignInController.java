package io.github.pedroermarinho.comandalivreapi.presenter.controllers.auth;

import io.github.pedroermarinho.comandalivreapi.domain.record.AuthRecord;
import io.github.pedroermarinho.comandalivreapi.domain.record.CredentialsRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.auth.SignIn;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.AuthPathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(PathRest.API + PathRest.VERSION + AuthPathRest.auth)
@CrossOrigin("*")
@Tag(name = "Autenticação", description = "Endpoints para gerênciamento de autenticação do usuário")
public class SignInController {

    private final SignIn signIn;

    public SignInController(SignIn signIn) {
        this.signIn = signIn;
    }

    @Operation(summary = "Realizar login")
    @Transactional
    @PostMapping(AuthPathRest.login)
    public ResponseEntity<AuthRecord> handleSignIn(@RequestBody @Valid CredentialsRecord credentialsRecord, HttpServletResponse response) {
        final var auth = signIn.execute(credentialsRecord);
        response.addHeader("Authorization", auth.token());
        return ResponseEntity.ok().body(auth);
    }
}
