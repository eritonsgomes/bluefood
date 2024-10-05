package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "restaurantes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Restaurante extends Usuario implements Serializable {

    private String cnpj;

}
