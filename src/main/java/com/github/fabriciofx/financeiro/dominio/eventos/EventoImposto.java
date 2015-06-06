package com.github.fabriciofx.financeiro.dominio.eventos;


public class EventoImposto extends EventoMonetario {
	private Evento base;

	public EventoImposto(Evento base, double valorTributavel) {
		super(valorTributavel, TipoEvento.IMPOSTO, base.getQuandoOcorreu(),
				base.getQuandoObservado(), base.getCliente());
		this.base = base;

		// Provavel recursao infinita
		assert (base.getTipoEvento() != getTipoEvento());
	}
}