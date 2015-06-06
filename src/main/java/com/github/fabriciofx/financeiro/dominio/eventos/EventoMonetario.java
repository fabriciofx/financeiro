package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;

public class EventoMonetario extends EventoContabil {
	private double valor;

	public EventoMonetario(double valor, TipoEvento tipo,
			LocalDate quandoOcorreu, LocalDate quandoObservado, Cliente cliente) {
		super(tipo, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}
}
