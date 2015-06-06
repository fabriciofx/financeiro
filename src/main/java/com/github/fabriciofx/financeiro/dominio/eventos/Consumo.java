package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;

public class Consumo extends EventoContabil {
	private double valor;

	public Consumo(double valor, LocalDate quandoOcorreu,
			LocalDate quandoObservado, Cliente cliente) {
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