package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.KWH;
import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.Consumo;

public class RegraBaixaRenda extends RegraLancamento {
	private double taxa;
	private KWH limiteDeConsumo;

	public RegraBaixaRenda(TipoLancamento tipo, double taxa, KWH limiteDeConsumo) {
		super(tipo);
		this.taxa = taxa;
		this.limiteDeConsumo = limiteDeConsumo;
	}

	protected Dinheiro calculaValor(Evento evento) {
		Consumo eventoDeConsumo = (Consumo) evento;
		double consumoAtual = eventoDeConsumo.getValor();

		return consumoAtual > limiteDeConsumo.valor() ? new Dinheiro(
				Double.toString(consumoAtual * eventoDeConsumo.getTaxa()))
				: new Dinheiro(Double.toString(consumoAtual * this.taxa));
	}
}