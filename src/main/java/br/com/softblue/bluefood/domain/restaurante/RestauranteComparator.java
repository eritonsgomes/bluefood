package br.com.softblue.bluefood.domain.restaurante;

import java.util.Comparator;

public class RestauranteComparator implements Comparator<Restaurante> {

    private final FiltroDeBusca filtroDeBusca;
    private final String cep;

    public RestauranteComparator(FiltroDeBusca filtroDeBusca, String cep) {
        this.filtroDeBusca = filtroDeBusca;
        this.cep = cep;
    }

    @Override
    public int compare(Restaurante r1, Restaurante r2) {
        int result;

        if (filtroDeBusca.getTipoDeOrdenacao() == FiltroDeBusca.TipoDeOrdenacao.TAXA) {
            result = r1.getTaxaDeEntregaBase().compareTo(r2.getTaxaDeEntregaBase());
        } else if (filtroDeBusca.getTipoDeOrdenacao() == FiltroDeBusca.TipoDeOrdenacao.TEMPO) {
            result = r1.calcularTempoDeEntrega(cep).compareTo(r2.calcularTempoDeEntrega(cep));
        } else {
            throw new IllegalStateException("O Tipo de Ordenação " + filtroDeBusca.getTipoDeOrdenacao() + " não é válido");
        }

        return filtroDeBusca.isAsc() ? result : -result;
    }

}