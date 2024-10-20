package br.com.softblue.bluefood.infrastructure.web.security;

import br.com.softblue.bluefood.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {

        Role role = Objects.requireNonNull(SecurityUtil.getLoggedUser()).getRole();

        if (role == Role.CLIENTE) {
            response.sendRedirect("/cliente/inicio");
        } else if (role == Role.RESTAURANTE) {
            response.sendRedirect("/restaurante/inicio");
        } else {
            throw new IllegalStateException("Usuário e/ou Senha inválido(s)");
        }

    }

}
