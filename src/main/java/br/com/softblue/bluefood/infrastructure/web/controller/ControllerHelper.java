package br.com.softblue.bluefood.infrastructure.web.controller;

import org.springframework.ui.Model;

public class ControllerHelper {
    
    public static void setEditMode(Model model, Boolean isEditMode) {
        model.addAttribute("isEditMode", isEditMode);
    }

}
