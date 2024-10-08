package br.com.softblue.bluefood.domain.cliente;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import br.com.softblue.bluefood.util.PasswordUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder
public class Cliente extends Usuario implements Serializable {

    @NotBlank(message = "O CPF não pode ser vazio")
    @Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotBlank(message = "O CEP não pode ser vazio")
    @Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
    @Column(length = 8)
    private String cep;
    
    public void encryptPassword() {
        String senhaCriptografada = PasswordUtil.encrypt(this.getSenha());
        this.setSenha(senhaCriptografada);
    }

}
