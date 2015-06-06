package com.github.fabriciofx.financeiro.dominio.eventos;

public class EventoImposto extends EventoMonetario {
	private Evento base;

	public EventoImposto(Evento base, double valorTributavel) {
		super(TipoEvento.IMPOSTO, base.getQuandoOcorreu(), base
				.getQuandoObservado(), base.getCliente(), valorTributavel);
		this.base = base;

		// Provavel recursao infinita
		assert (base.getTipoEvento() != getTipoEvento());
	}
}