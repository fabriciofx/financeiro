package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoContabil;

public class RegraMultiplicaPorTaxa extends RegraLancamento {
	public RegraMultiplicaPorTaxa(TipoLancamento tipo) {
		super(tipo);
	}

	protected double calculaValor(EventoContabil evento) {
		Consumo eventoDeConsumo = (Consumo) evento;

		return eventoDeConsumo.getValor() * eventoDeConsumo.getTaxa();
	}
}
