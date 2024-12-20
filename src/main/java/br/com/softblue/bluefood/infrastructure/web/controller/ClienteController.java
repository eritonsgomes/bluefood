package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.application.exception.EmailValidationException;
import br.com.softblue.bluefood.application.service.ClienteService;
import br.com.softblue.bluefood.application.service.RestauranteService;
import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.FiltroDeBusca;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;
    private final RestauranteService restauranteService;
    private final CategoriaRestauranteRepository categoriaRestauranteRepository;

    @GetMapping(path = "/inicio")
    public String inicio(Model model) {
        List<CategoriaRestaurante> categorias = categoriaRestauranteRepository.findAll(Sort.by("nome"));
        model.addAttribute("categorias", categorias);
        model.addAttribute("filtroDeBusca", new FiltroDeBusca());

        return "cliente/inicio";
    }

    @GetMapping(path = "/edita-cadastro")
    public String editaCliente(Model model) {
        Cliente usuario = SecurityUtil.getLoggedCliente();
        Optional<Cliente> cliente = clienteRepository.findById(usuario.getId());
        ControllerHelper.setEditMode(Boolean.TRUE, model);
        model.addAttribute("cliente", cliente.get());

        return "cliente/cad-cliente";
    }

    @PostMapping(path = "/salva-cadastro")
    public String salvaCliente(@ModelAttribute(name = "cliente") @Valid Cliente cliente,
        Errors errors, Model model) {

        if (!errors.hasErrors()) {
            try {
                clienteService.save(cliente);
                model.addAttribute("msg", "Cadastro alterado com sucesso");
            } catch (EmailValidationException e) {
                errors.rejectValue("email", "400", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(Boolean.TRUE, model);

        return "cliente/cad-cliente";
    }

    @GetMapping(path = "/busca")
    public String busca(@ModelAttribute(name = "filtroDeBusca") FiltroDeBusca filtroDeBusca,
        @RequestParam(value = "cmd", required = false) String comando, Model model) {

        filtroDeBusca.processaFiltro(comando);
        model.addAttribute("filtroDeBusca", filtroDeBusca);

        List<CategoriaRestaurante> categorias = categoriaRestauranteRepository.findAll(Sort.by("nome"));
        model.addAttribute("categorias", categorias);

        List<Restaurante> restaurantesEncontrados = restauranteService.searchByFilter(filtroDeBusca);
        model.addAttribute("restaurantes", restaurantesEncontrados);

        return "cliente/busca";

    }

}
