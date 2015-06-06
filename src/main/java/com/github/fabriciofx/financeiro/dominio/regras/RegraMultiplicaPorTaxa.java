package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.Evento;

public class RegraMultiplicaPorTaxa extends RegraLancamento {
	public RegraMultiplicaPorTaxa(TipoLancamento tipo) {
		super(tipo);
	}

	protected double calculaValor(Evento evento) {
		Consumo eventoDeConsumo = (Consumo) evento;

		return eventoDeConsumo.getValor() * eventoDeConsumo.getTaxa();
	}
}
