package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = { "/autenticacao/login" })
    public String login() {
        return "autenticacao/login";
    }

}
