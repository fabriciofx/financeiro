package com.github.fabriciofx.financeiro.dominio;

import com.github.fabriciofx.financeiro.dominio.eventos.Evento;
import com.github.fabriciofx.financeiro.dominio.eventos.EventoImposto;

public abstract class RegraLancamento {
	protected TipoLancamento tipo;

	protected RegraLancamento(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	private void facaLancamento(Evento evento, double valor) {
		Lancamento novoLancamento = new Lancamento(valor,
				evento.getQuandoObservado(), tipo);
		evento.getCliente().addLancamento(novoLancamento);
		evento.addLancamentoResultante(novoLancamento);
	}

	private boolean isTributavel() {
		return tipo != TipoLancamento.IMPOSTO;
	}

	public void processa(Evento evento) {
		facaLancamento(evento, calculaValor(evento));

		if (isTributavel()) {
			new EventoImposto(evento, calculaValor(evento)).processa();
		}
	}

	protected abstract double calculaValor(Evento evento);
}