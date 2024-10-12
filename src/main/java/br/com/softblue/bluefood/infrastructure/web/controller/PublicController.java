package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.application.ClienteService;
import br.com.softblue.bluefood.application.EmailValidationException;
import br.com.softblue.bluefood.application.RestauranteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public")
@RequiredArgsConstructor
public class PublicController {

    private final ClienteService clienteService;
    private final RestauranteService restauranteService;
    private final CategoriaRestauranteRepository categoriaRestauranteRepository;


    @GetMapping(path = "/cliente/novo-cadastro")
    public String novoCliente(Model model) {
        Cliente novoCliente = Cliente.builder().build();

        model.addAttribute("cliente", novoCliente);
        ControllerHelper.setEditMode(Boolean.FALSE, model);

        return "cliente/cad-cliente";
    }

    @PostMapping(path = "/cliente/salva-cadastro")
    public String salvaCliente(@ModelAttribute(name = "cliente") @Valid Cliente cliente,
        Errors errors, Model model) {

        if (!errors.hasErrors()) {
            try {
                clienteService.save(cliente);
                model.addAttribute("msg", "Cliente cadastrado com sucesso");
            } catch (EmailValidationException e) {
                errors.rejectValue("email", "400", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(Boolean.FALSE, model);

        return "cliente/cad-cliente";
    }

    @GetMapping(path = "/restaurante/novo-cadastro")
    public String novoRestaurante(Model model) {
        Restaurante novoRestaurante = Restaurante.builder().build();

        model.addAttribute("restaurante", novoRestaurante);
        ControllerHelper.setEditMode(Boolean.FALSE, model);
        ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);

        return "restaurante/cad-restaurante";
    }

    @PostMapping(path = "/restaurante/salva-cadastro")
    public String salvaRestaurante(@ModelAttribute(name = "restaurante") @Valid Restaurante restaurante,
        Errors errors, Model model) {

        if (!errors.hasErrors()) {
            try {
                restauranteService.save(restaurante);
                model.addAttribute("msg", "Restaurante cadastrado com sucesso");
            } catch (EmailValidationException e) {
                errors.rejectValue("email", "400", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(Boolean.FALSE, model);
        ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);

        return "restaurante/cad-restaurante";
    }

}
