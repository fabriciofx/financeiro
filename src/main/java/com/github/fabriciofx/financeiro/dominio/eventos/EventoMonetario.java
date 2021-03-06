package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;
import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.TipoEvento;

public class EventoMonetario extends Evento {
	private Dinheiro valor;

	public EventoMonetario(TipoEvento tipo, LocalDate quandoOcorreu,
			LocalDate quandoObservado, Cliente cliente, Dinheiro valor) {
		super(tipo, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public Dinheiro getValor() {
		return valor;
	}
}
