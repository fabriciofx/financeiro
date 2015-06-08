package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;

public class RegraMultiplicaPorTaxa extends RegraLancamento {
	public RegraMultiplicaPorTaxa(TipoLancamento tipo) {
		super(tipo);
	}

	protected Dinheiro calculaValor(Evento evento) {
		Consumo eventoDeConsumo = (Consumo) evento;

		return new Dinheiro(Double.toString(eventoDeConsumo.getValor()
				* eventoDeConsumo.getTaxa()));
	}
}
