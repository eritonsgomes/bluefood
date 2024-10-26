package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import br.com.softblue.bluefood.infrastructure.web.validator.LogotipoRestauranteUploadConstraint;
import br.com.softblue.bluefood.util.FileType;
import br.com.softblue.bluefood.util.PasswordUtil;
import br.com.softblue.bluefood.util.StringUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    private String nomeDoArquivoLogotipo;

    @LogotipoRestauranteUploadConstraint(acceptedTypes = { FileType.JPG, FileType.PNG })
    private transient MultipartFile arquivoLogotipo;

    @NotNull(message = "A Taxa de Entrega não pode ser vazia")
    @Min(0)
    @Max(99)
    private BigDecimal taxaDeEntregaBase;

    @NotNull(message = "O Tempo de Entrega não pode ser vazio")
    @Min(0)
    @Max(120)
    private Integer tempoDeEntrega;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurante_categoria_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
    )
    @Size(min = 1, message = "O Restaurante precisa ter pelo menos uma Categoria")
    @ToString.Exclude
    @Builder.Default
    private Set<CategoriaRestaurante> categorias = new HashSet<>(0);

    public void setNomeDoArquivoDoLogotipo() {
        if (this.getId() == null) {
            throw new IllegalArgumentException("O ID do Registro não pode ser nulo");
        }

        FileType fileType = FileType.of(arquivoLogotipo.getContentType());

        if (fileType != null) {
            this.nomeDoArquivoLogotipo = String.format("img-logo-rest-%d.%s", getId(), fileType.getExtension());
        }
    }

    public void encryptPassword() {
        String senhaCriptografada = PasswordUtil.encrypt(this.getSenha());
        this.setSenha(senhaCriptografada);
    }

    public String getCategoriasAsText() {
        Set<String> categorias = new LinkedHashSet<>(0);

        for (CategoriaRestaurante categoria: this.categorias) {
            categorias.add(categoria.getNome());
        }

        return StringUtil.concatenate(categorias);
    }

    public Integer calcularTempoDeEntrega(String cep) {
        int soma = 0;

        for (char c : cep.toCharArray()) {
            int v = Character.getNumericValue(c);
            if (v > 0) {
                soma += v;
            }
        }

        soma /= 2;

        return tempoDeEntrega + soma;
    }

}
