package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.Evento;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;

public class RegraFormulaSimples extends RegraLancamento {
	private double multiplicador;
	private double valorFixo;

	public RegraFormulaSimples(double multiplicador, double valorFixo,
			TipoLancamento tipo) {
		super(tipo);
		this.multiplicador = multiplicador;
		this.valorFixo = valorFixo;
	}

	protected double calculaValor(Evento evento) {
		double valorDoEvento = ((EventoMonetario) evento).getValor();

		return valorDoEvento * multiplicador + valorFixo;
	}
}