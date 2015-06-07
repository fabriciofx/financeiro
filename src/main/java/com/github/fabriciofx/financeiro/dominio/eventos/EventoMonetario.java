package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.TipoEvento;

public class EventoMonetario extends Evento {
	private double valor;

	public EventoMonetario(TipoEvento tipo, LocalDate quandoOcorreu,
			LocalDate quandoObservado, Cliente cliente, double valor) {
		super(tipo, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}
}
