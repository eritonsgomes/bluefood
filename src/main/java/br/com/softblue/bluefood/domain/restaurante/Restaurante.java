package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import br.com.softblue.bluefood.util.PasswordUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurantes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder
public class Restaurante extends Usuario implements Serializable {

    @NotBlank(message = "O CNPJ não pode ser vazio")
    @Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
    @Column(length = 14, nullable = false)
    private String cnpj;

    private String logotipo;

    private transient MultipartFile arquivoLogotipo;

    @NotNull(message = "A Taxa de Entrega não pode ser vazia")
    @Min(0)
    @Max(99)
    private BigDecimal taxaEntregaBase;

    @NotNull(message = "O Tempo de Entrega não pode ser vazio")
    @Min(0)
    @Max(120)
    private Integer tempoEntrega;

    @ManyToMany
    @JoinTable(
            name = "restaurante_categoria_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
    )
    @Size(min = 1, message = "O Restaurante precisa ter pelo menos uma Categoria")
    @ToString.Exclude
    @Builder.Default
    private Set<CategoriaRestaurante> categorias = new HashSet<>(0);

    public void encryptPassword() {
        String senhaCriptografada = PasswordUtil.encrypt(this.getSenha());
        this.setSenha(senhaCriptografada);
    }

}
