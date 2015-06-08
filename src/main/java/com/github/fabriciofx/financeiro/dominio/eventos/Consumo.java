package com.github.fabriciofx.financeiro.dominio.eventos;

import java.time.LocalDate;

import com.github.fabriciofx.financeiro.dominio.Cliente;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.KWH;
import com.github.fabriciofx.financeiro.dominio.TipoEvento;

public class Consumo extends Evento {
	private KWH kwh;

	public Consumo(LocalDate quandoOcorreu, LocalDate quandoObservado,
			Cliente cliente, KWH kwh) {
		super(TipoEvento.CONSUMO, quandoOcorreu, quandoObservado, cliente);
		this.kwh = kwh;
	}

	public double getValor() {
		return kwh.valor();
	}

	public double getTaxa() {
		return getCliente().getAcordoServico().getTaxa();
	}
}