package com.github.fabriciofx.financeiro.dominio;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.regras.TipoLancamento;

public class Lancamento {
	private TipoLancamento tipo;
	private LocalDate data;
	private double valor;

	public Lancamento(TipoLancamento tipo, LocalDate data, double valor) {
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
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