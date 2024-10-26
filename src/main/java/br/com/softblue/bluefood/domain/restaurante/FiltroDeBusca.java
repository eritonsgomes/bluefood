package br.com.softblue.bluefood.domain.restaurante;

import br.com.softblue.bluefood.util.StringUtil;
import lombok.Data;

@Data
public class FiltroDeBusca {

    public enum TipoDeBusca {
        TEXTO, CATEGORIA
    }

    public enum TipoDeOrdenacao {
        TAXA, TEMPO
    }

    public enum Comando {
        ENTREGA_GRATIS, MENOR_TAXA, MAIOR_TAXA, MENOR_TEMPO, MAIOR_TEMPO
    }

    private String texto;
    private TipoDeBusca tipoDeBusca;
    private Long categoriaId;
    private TipoDeOrdenacao tipoDeOrdenacao = TipoDeOrdenacao.TAXA;
    private boolean isAsc = true;
    private boolean isEntregaGratis = false;

    public void processaFiltro(String comando) {
        if (tipoDeBusca == TipoDeBusca.TEXTO) {
            categoriaId = null;
        } else if (tipoDeBusca == TipoDeBusca.CATEGORIA) {
            texto = null;
        }

        if (!StringUtil.isEmpty(comando)) {
            Comando cmd = Comando.valueOf(comando);

            if (cmd == Comando.ENTREGA_GRATIS) {
                isEntregaGratis = !isEntregaGratis;
            } else if (cmd == Comando.MENOR_TAXA) {
                tipoDeOrdenacao = TipoDeOrdenacao.TAXA;
                isAsc = true;
            } else if (cmd == Comando.MAIOR_TAXA) {
                tipoDeOrdenacao = TipoDeOrdenacao.TAXA;
                isAsc = false;
            } else if (cmd == Comando.MENOR_TEMPO) {
                tipoDeOrdenacao = TipoDeOrdenacao.TEMPO;
                isAsc = true;
            } else if (cmd == Comando.MAIOR_TEMPO) {
                tipoDeOrdenacao = TipoDeOrdenacao.TEMPO;
                isAsc = false;
            }

        }
    }

}
