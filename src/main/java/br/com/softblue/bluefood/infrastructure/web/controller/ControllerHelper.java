package br.com.softblue.bluefood.infrastructure.web.controller;

import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

public class ControllerHelper {

    public static void setEditMode(boolean isEditMode, Model model) {
        model.addAttribute("isEditMode", isEditMode);
    }

    public static void addCategoriasToRequest(CategoriaRestauranteRepository categoriaRestauranteRepository, Model model) {
        model.addAttribute("categorias", categoriaRestauranteRepository.findAll(Sort.by( "nome")));
    }

}
