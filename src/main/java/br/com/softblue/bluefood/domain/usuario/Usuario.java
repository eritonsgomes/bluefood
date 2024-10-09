package br.com.softblue.bluefood.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "O Nome não pode ser vazio")
    @Size(max = 80, message = "O Nome é muito grande")
    private String nome;

    @NotBlank(message = "O E-mail não pode ser vazio")
    @Size(max = 60, message = "O E-mail é muito grande")
    @Email(message = "O E-mail é inválido")
    private String email;

    @NotBlank(message = "A Senha não pode ser vazia")
    @Size(max = 80, message = "A Senha é muito grande")
    private String senha;

    @NotBlank(message = "o Telefone não pode ser vazio")
    @Pattern(regexp = "[0-9]{10,11}", message = "o Telefone possui formato inválido")
    @Column(length = 11, nullable = false)
    private String telefone;

}
