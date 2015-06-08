package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;

public class Lancamento {
	private TipoLancamento tipo;
	private LocalDate data;
	private Dinheiro valor;

	public Lancamento(TipoLancamento tipo, LocalDate data, Dinheiro valor) {
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
	}

	public Dinheiro getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}
}