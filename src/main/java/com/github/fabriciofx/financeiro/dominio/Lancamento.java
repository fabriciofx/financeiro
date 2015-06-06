package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;

public class Lancamento {
	private LocalDate data;
	private TipoLancamento tipo;
	private double valor;

	public Lancamento(double valor, LocalDate data, TipoLancamento tipo) {
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}
}