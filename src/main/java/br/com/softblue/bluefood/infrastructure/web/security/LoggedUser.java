package br.com.softblue.bluefood.infrastructure.web.security;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.usuario.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class LoggedUser implements UserDetails {

    private final Usuario usuario;
    private final Role role;
    private final Collection<? extends GrantedAuthority> roles;

    public LoggedUser(Usuario usuario) {
        this.usuario = usuario;

        Role role;

        if (usuario instanceof Cliente) {
            role = Role.CLIENTE;
        } else if (usuario instanceof Restaurante) {
            role = Role.RESTAURANTE;
        } else {
            throw new IllegalStateException("O Perfil do Usuário não é válido");
        }

        this.role = role;
        this.roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
