package com.fabriciocabral.financeiro.dominio;

public class EventoImposto extends EventoMonetario {
	private EventoContabil base;

	public EventoImposto(EventoContabil base, double valorTributavel) {
		super(valorTributavel, TipoEvento.IMPOSTO, base.getQuandoOcorreu(),
				base.getQuandoObservado(), base.getCliente());
		this.base = base;

		// Provavel recursao infinita
		assert (base.getTipoEvento() != getTipoEvento());
	}
}