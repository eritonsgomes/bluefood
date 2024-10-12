package br.com.softblue.bluefood.domain.restaurante;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "categoria_restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CategoriaRestaurante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "O Nome da Categoria não pode ser vazio")
    @Size(max = 20, message = "O Nome da Categoria é muito grande")
    private String nome;

    @NotBlank(message = "O Caminho da Imagem não pode ser vazio")
    @Size(max = 50, message = "O Caminho da Imagem é muito grande")
    private String imagem;

}
