package br.com.softblue.bluefood.domain.restaurante;

import lombok.Data;

@Data
public class FiltroDeBusca {

    public enum TipoDeBusca {
        TEXTO, CATEGORIA
    }

    private String texto;
    private TipoDeBusca tipoDeBusca;
    private Long categoriaId;

    public void processaFiltro() {
        if (tipoDeBusca == TipoDeBusca.TEXTO) {
            categoriaId = null;
        } else if (tipoDeBusca == TipoDeBusca.CATEGORIA) {
            texto = null;
        }
    }

}
