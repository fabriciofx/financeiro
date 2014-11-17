package com.fabriciocabral.financeiro.dominio;

public abstract class RegraLancamento {
	protected TipoLancamento tipo;

	protected RegraLancamento(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	private void facaLancamento(EventoContabil evento, double valor) {
		Lancamento novoLancamento = new Lancamento(valor,
				evento.getQuandoObservado(), tipo);
		evento.getCliente().addLancamento(novoLancamento);
		evento.addLancamentoResultante(novoLancamento);
	}

	private boolean isTributavel() {
		return tipo != TipoLancamento.IMPOSTO;
	}

	public void processa(EventoContabil evento) {
		facaLancamento(evento, calculaValor(evento));

		if (isTributavel()) {
			new EventoImposto(evento, calculaValor(evento)).processa();
		}
	}

	protected abstract double calculaValor(EventoContabil evento);
}