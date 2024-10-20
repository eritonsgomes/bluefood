package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping(path = { "/autenticacao/login" })
    public String login() {
        return "autenticacao/login";
    }

    @GetMapping(path = "/autenticacao/login/error")
    public String loginError(Model model) {
        model.addAttribute("msg", "Usuário e Senha inválidos");

        return "autenticacao/login";
    }

    @GetMapping(path = "/autenticacao/logout/success")
    public String loginSuccess(Model model) {
        model.addAttribute("msg", "O Usuário foi desconectado");

        return "autenticacao/login";
    }

}
