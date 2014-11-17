package com.fabriciocabral.financeiro.dominio;

import java.util.Date;

public class Consumo extends EventoContabil {
	private double valor;

	public Consumo(double valor, Date quandoOcorreu, Date quandoObservado,
			Cliente cliente) {
		super(TipoEvento.CONSUMO, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	double getTaxa() {
		return getCliente().getAcordoServico().getTaxa();
	}
}