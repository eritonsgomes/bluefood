package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {

    @GetMapping(path = "/inicio")
    public String inicio() {
        return "cliente/inicio";
    }

}
