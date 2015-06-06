package com.github.fabriciofx.financeiro.dominio;

public class RegraBaixaRenda extends RegraLancamento {
	private double taxa;
	private double limiteDeConsumo;

	public RegraBaixaRenda(TipoLancamento tipo, double taxa,
			double limiteDeConsumo) {
		super(tipo);
		this.taxa = taxa;
		this.limiteDeConsumo = limiteDeConsumo;
	}

	protected double calculaValor(EventoContabil evento) {
		Consumo eventoDeConsumo = (Consumo) evento;
		double consumoAtual = eventoDeConsumo.getValor();

		return consumoAtual > limiteDeConsumo ? consumoAtual
				* eventoDeConsumo.getTaxa() : consumoAtual * this.taxa;
	}
}