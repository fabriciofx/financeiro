package com.github.fabriciofx.financeiro.dominio.regras;

public enum TipoLancamento {
	CONSUMO_BASICO("Consumo Básico"), SERVICO("Taxa de Serviço"), IMPOSTO(
			"Imposto"), TRANSACAO("Transação");

	private final String nome;

	TipoLancamento(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
