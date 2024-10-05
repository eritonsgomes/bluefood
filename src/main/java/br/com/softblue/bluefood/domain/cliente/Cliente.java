package br.com.softblue.bluefood.domain.cliente;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "clientes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Cliente extends Usuario implements Serializable {

    private String cpf;
    private String cep;

}
