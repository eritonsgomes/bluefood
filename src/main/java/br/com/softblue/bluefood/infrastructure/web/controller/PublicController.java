package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.application.ClienteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/cliente/novo-cadastro")
    public String novoCliente(Model model) {
        Cliente novoCliente = Cliente.builder().build();

        model.addAttribute("cliente", novoCliente);
        ControllerHelper.setEditMode(model, Boolean.FALSE);

        return "cliente/cad-cliente";
    }

    @PostMapping(path = "/cliente/salva-cadastro")
    public String salvaCliente(@ModelAttribute(name = "cliente") @Valid Cliente cliente,
        Errors errors, Model model) {

        if (!errors.hasErrors()) {
            clienteService.save(cliente);
            model.addAttribute("msg", "Cliente cadastrado com sucesso");
        }

        ControllerHelper.setEditMode(model, Boolean.FALSE);

        return "cliente/cad-cliente";
    }

}
