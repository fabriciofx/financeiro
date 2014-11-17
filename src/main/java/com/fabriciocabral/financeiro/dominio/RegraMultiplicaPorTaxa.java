package com.fabriciocabral.financeiro.dominio;

public class RegraMultiplicaPorTaxa extends RegraLancamento {
	public RegraMultiplicaPorTaxa(TipoLancamento tipo) {
		super(tipo);
	}

	protected double calculaValor(EventoContabil evento) {
		Consumo eventoDeConsumo = (Consumo) evento;

		return eventoDeConsumo.getValor() * eventoDeConsumo.getTaxa();
	}
}
