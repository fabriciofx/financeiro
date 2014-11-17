package com.fabriciocabral.financeiro.dominio;

import java.util.Date;

public class Lancamento {
	private Date data;
	private TipoLancamento tipo;
	private double valor;

	public Lancamento(double valor, Date data, TipoLancamento tipo) {
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public Date getData() {
		return data;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}
}