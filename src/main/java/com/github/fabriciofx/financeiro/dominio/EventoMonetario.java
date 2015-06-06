package com.github.fabriciofx.financeiro.dominio;

import java.util.Date;

public class EventoMonetario extends EventoContabil {
	private double valor;

	public EventoMonetario(double valor, TipoEvento tipo, Date quandoOcorreu,
			Date quandoObservado, Cliente cliente) {
		super(tipo, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}
}
