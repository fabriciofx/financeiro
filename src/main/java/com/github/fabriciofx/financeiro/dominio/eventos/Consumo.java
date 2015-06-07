package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.TipoEvento;

public class Consumo extends Evento {
	private double valor;

	public Consumo(LocalDate quandoOcorreu, LocalDate quandoObservado,
			Cliente cliente, double valor) {
		super(TipoEvento.CONSUMO, quandoOcorreu, quandoObservado, cliente);
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public double getTaxa() {
		return getCliente().getAcordoServico().getTaxa();
	}
}