package com.github.fabriciofx.financeiro.dominio.eventos;

import com.github.fabriciofx.financeiro.dominio.Dinheiro;
import com.github.fabriciofx.financeiro.dominio.Evento;
import com.github.fabriciofx.financeiro.dominio.TipoEvento;

public class EventoImposto extends EventoMonetario {
	private Evento base;

	public EventoImposto(Evento base, Dinheiro valorTributavel) {
		super(TipoEvento.IMPOSTO, base.getQuandoOcorreu(), base
				.getQuandoObservado(), base.getCliente(), valorTributavel);
		this.base = base;

		// Provavel recursao infinita
		assert (base.getTipoEvento() != getTipoEvento());
	}
}