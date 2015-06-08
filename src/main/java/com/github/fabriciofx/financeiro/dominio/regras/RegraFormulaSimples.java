package com.github.fabriciofx.financeiro.dominio.regras;

import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.RegraLancamento;
import com.github.fabriciofx.financeiro.dominio.TipoLancamento;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoMonetario;

public class RegraFormulaSimples extends RegraLancamento {
	private double multiplicador;
	private Dinheiro valorFixo;

	public RegraFormulaSimples(TipoLancamento tipo, double multiplicador,
			Dinheiro valorFixo) {
		super(tipo);
		this.multiplicador = multiplicador;
		this.valorFixo = valorFixo;
	}

	protected Dinheiro calculaValor(Evento evento) {
		Dinheiro valorDoEvento = ((EventoMonetario) evento).getValor();

		return valorDoEvento.multiplica(multiplicador).soma(valorFixo);
	}
}