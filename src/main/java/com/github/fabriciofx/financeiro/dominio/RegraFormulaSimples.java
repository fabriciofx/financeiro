package com.github.fabriciofx.financeiro.dominio;

public class RegraFormulaSimples extends RegraLancamento {
	private double multiplicador;
	private double valorFixo;

	public RegraFormulaSimples(double multiplicador, double valorFixo,
			TipoLancamento tipo) {
		super(tipo);
		this.multiplicador = multiplicador;
		this.valorFixo = valorFixo;
	}

	protected double calculaValor(EventoContabil evento) {
		double valorDoEvento = ((EventoMonetario) evento).getValor();

		return valorDoEvento * multiplicador + valorFixo;
	}
}