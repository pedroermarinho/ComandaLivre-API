package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import io.github.pedroermarinho.comandalivreapi.domain.record.UserRecord;
import io.github.pedroermarinho.comandalivreapi.domain.usecases.user.SearchUser;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = PathRest.API + PathRest.VERSION + UserPathRest.USER_SEARCH)
@Tag(name = "Usuário", description = "Operações do usuário")
public class UserSearchController {

    private final SearchUser searchUser;

    public UserSearchController(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Operation(summary = "Lista de usuários", tags = {"Usuário"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserRecord.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<UserRecord>> searchUserAll() {
        final List<UserRecord> users = searchUser.searchUserAll();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Buscar usuário por id", tags = {"Usuário"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = UserRecord.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> searchUserById(@PathVariable UUID id) {
        final UserRecord user = searchUser.searchUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Buscar usuário por email", tags = {"Usuário"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = UserRecord.class))
            )
    })
    @GetMapping(UserPathRest.USER_SEARCH_EMAIL + "/{email}")
    public ResponseEntity<UserRecord> searchUserByEmail(@PathVariable String email) {
        final UserRecord user = searchUser.searchUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Buscar usuário por username", tags = {"Usuário"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(
                    responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(schema = @Schema(implementation = UserRecord.class))
            )
    })
    @GetMapping(UserPathRest.USER_SEARCH_USERNAME + "/{username}")
    public ResponseEntity<UserRecord> searchUserByUsername(@PathVariable String username) {
        final UserRecord user = searchUser.searchUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

}
