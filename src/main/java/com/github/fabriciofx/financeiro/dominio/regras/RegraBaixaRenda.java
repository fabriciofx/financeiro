package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;
import com.github.fabriciofx.financeiro.dominio.eventos.Evento;

public class RegraBaixaRenda extends RegraLancamento {
	private double taxa;
	private double limiteDeConsumo;

	public RegraBaixaRenda(TipoLancamento tipo, double taxa,
			double limiteDeConsumo) {
		super(tipo);
		this.taxa = taxa;
		this.limiteDeConsumo = limiteDeConsumo;
	}

	protected double calculaValor(Evento evento) {
		Consumo eventoDeConsumo = (Consumo) evento;
		double consumoAtual = eventoDeConsumo.getValor();

		return consumoAtual > limiteDeConsumo ? consumoAtual
				* eventoDeConsumo.getTaxa() : consumoAtual * this.taxa;
	}
}