package io.github.pedroermarinho.comandalivreapi.presenter.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedroermarinho.comandalivreapi.infra.config.constants.PathRest;
import io.github.pedroermarinho.comandalivreapi.infra.config.constants.UserPathRest;

@RestController
@RequestMapping(value = PathRest.API+PathRest.VERSION+UserPathRest.USER_UPDATE)
public class UpdateUserController {
    
}
