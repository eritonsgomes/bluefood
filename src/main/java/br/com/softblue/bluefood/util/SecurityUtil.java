package br.com.softblue.bluefood.util;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.infrastructure.web.security.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class SecurityUtil {

    public static LoggedUser getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }

    public static Cliente getLoggedCliente() {
        LoggedUser loggedUser = getLoggedUser();

        if (loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado");
        }

        if (!(loggedUser.getUsuario() instanceof Cliente)) {
            throw new IllegalStateException("O usuário logado não é um cliente");
        }

        return (Cliente) loggedUser.getUsuario();
    }

    public static Restaurante getLoggedRestaurante() {
        LoggedUser loggedUser = getLoggedUser();

        if (loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado");
        }

        if (!(loggedUser.getUsuario() instanceof Restaurante)) {
            throw new IllegalStateException("O usuário logado não é um restaurante");
        }

        return (Restaurante) loggedUser.getUsuario();
    }

}
